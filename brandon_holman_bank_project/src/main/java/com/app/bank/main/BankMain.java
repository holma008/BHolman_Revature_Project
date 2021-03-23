package com.app.bank.main;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.bank.account.service.AccountService;
import com.app.bank.account.service.impl.AccountServiceImpl;
import com.app.bank.customer.service.CustomerService;
import com.app.bank.customer.service.impl.CustomerServiceImpl;
import com.app.bank.employee.service.EmployeeService;
import com.app.bank.employee.service.impl.EmployeeServiceImpl;
import com.app.bank.model.Account;
import com.app.bank.model.Transaction;
import com.app.bank.model.UnapprovedAccount;
import com.app.bank.model.User;
import com.app.bank.transaction.service.TransactionService;
import com.app.bank.transaction.service.impl.TransactionServiceImpl;
import com.app.bank.user.service.UserService;
import com.app.bank.user.service.impl.UserServiceImpl;

import com.app.bank.exception.BusinessException;

public class BankMain {

	private static Logger log = Logger.getLogger(BankMain.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int ch = 0;
		UserService userService = new UserServiceImpl();
		CustomerService customerService = new CustomerServiceImpl();
		AccountService accountService = new AccountServiceImpl();
		TransactionService transactionService = new TransactionServiceImpl();
		int accNum = 0;
		do {
			// main menu
			log.info("Bank Menu");
			log.info("------------");
			log.info("1) create customer account");
			log.info("2) log in to existing account");
			log.info("3) exit program");
			log.info("please enter an appropriate option(1-3)");

			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.warn("choice should be number only");
			}
			switch (ch) {
			case 1:
				// create customer account
				User user = new User();
				// get user info
				log.info("enter name");
				user.setName(scanner.nextLine());
				log.info("enter email");
				user.setEmail(scanner.nextLine());
				log.info("choose password, must contain at least 1 number and no spaces");
				user.setPassword(scanner.nextLine());
				log.info("enter 10 digit phone number");
				user.setPhoneNumber(scanner.nextLine());
				log.info("enter address");
				user.setAddress(scanner.nextLine());
				log.info("enter date of birth mm-dd-yyyy");
				user.setDateOfBirth(
						Date.valueOf(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
				try {
					if (userService.registerCustomer(user) == 1) {
						log.info("Customer registered successfully");
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
				break;
			case 2:
				// login
				log.info("enter email");
				String email = scanner.nextLine();
				log.info("enter password");
				String password = scanner.nextLine();
				User u = new User();// retrieve user information
				try {
					u = userService.logIn(email, password);
					if (u != null) {
						log.info("login successful");
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					break;
				}
				if (u.getUserType().matches("c")) {// if user is customer
					int cu = 0;
					double balance = 0;
					do {
						log.info("Customer Options Menu");
						log.info("------------------------");
						log.info("1) apply for account");
						log.info("2) view balance of account");
						log.info("3) make withdrawl");
						log.info("4) make deposit");
						log.info("5) transfer money");
						log.info("6) return to main menu");
						log.info("please enter an appropriate option(1-6)");
						cu = Integer.parseInt(scanner.nextLine());
						switch (cu) {
						case 1:
							// apply for account with starting balance
							Account account = new Account();
							log.info("Applying for an account\nEnter starting balance of account");
							// get account details
							try {
								balance = Integer.parseInt(scanner.nextLine());
								account.setBalance(balance);
								account.setAccountHolderId(u.getUserId());
								try {
									if (customerService.createAccount(account) == 1) {
										log.info(
												"account successfully applied for, an employee will review and approve or reject");
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
							} catch (NumberFormatException e) {
								log.warn("choice should be number only");
							}
							break;
						case 2:
							// view balance of account
							log.info("enter the number of the account you wish to view the balance of");
							try {
								accNum = Integer.parseInt(scanner.nextLine());
								try {
									if (!accountService.accountExists(u.getUserId(), accNum)) {
										log.info(
												"account number " + accNum + " for user " + u.getName() + " not found");
										break;
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								balance = customerService.checkBalance(u.getUserId(), accNum);
								if (balance == 0) {
									log.warn("account not yet approved");
									break;
								}
								log.info("the balance of your account number " + accNum + ": " + balance);
							} catch (NumberFormatException e) {
								log.warn("choice should be number only");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
							}
							break;
						case 3:
							// make withdrawal
							log.info("enter the number of the account from which you wish to withdraw funds");
							try {
								accNum = Integer.parseInt(scanner.nextLine());
								if (!accountService.accountExists(u.getUserId(), accNum)) {
									log.info("account number " + accNum + " for user " + u.getName() + " not found");
									break;
								}
								balance = customerService.checkBalance(u.getUserId(), accNum);
								if (balance == 0) {
									log.warn("account not yet approved");
									break;
								}
								log.info("current balance of your account number " + accNum + ": " + balance);
							} catch (NumberFormatException e) {
								log.warn("choice should be number only");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
							}
							log.info("enter ammount to withdraw");
							try {
								double withdraw = Integer.parseInt(scanner.nextLine());
								if (withdraw > balance) {
									log.warn("invalid entry, account does not have sufficient funds");
								} else if (withdraw < 0) {
									log.warn("invalid entry, cannot withdraw negative funds");
								} else {
									double remainder = balance - withdraw;
									try {
										if (transactionService.WithdrawalMade(u.getUserId(), accNum, withdraw) == 1) {
											try {
												if (accountService.updateAccountBalance(accNum, remainder) == 1) {
													balance = customerService.checkBalance(u.getUserId(), accNum);
													log.info("withdrawl successful, new balance: " + balance);
												}
											} catch (BusinessException e) {
												log.warn(e.getMessage());
											} catch (NumberFormatException e) {
												log.warn("choice should be number only");
											}
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									} catch (NumberFormatException e) {
										log.warn("choice should be number only");
									}
								}
							} catch (NumberFormatException e) {
								log.warn("choice should be number only");
							}
							break;
						case 4:
							// make deposit
							log.info("enter the number of the account into which you wish to deposit funds");
							try {
								accNum = Integer.parseInt(scanner.nextLine());
								if (!accountService.accountExists(u.getUserId(), accNum)) {
									log.warn("account number " + accNum + " for user " + u.getName() + " not found");
									break;
								}
								balance = customerService.checkBalance(u.getUserId(), accNum);
								if (balance == 0) {
									log.warn("account not yet approved");
									break;
								}
								log.info("current balance of your account number " + accNum + ": " + balance);
							} catch (NumberFormatException e) {
								log.warn("choice should be number only");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
							}
							log.info("enter ammount to deposit");
							try {
								double deposit = Integer.parseInt(scanner.nextLine());
								if (deposit < 0) {
									log.warn("invalid entry, cannot deposit negative funds");
								} else {
									double remainder = balance + deposit;
									try {
										if (transactionService.DepositMade(u.getUserId(), accNum, deposit) == 1) {

											try {
												if (accountService.updateAccountBalance(accNum, remainder) == 1) {
													balance = customerService.checkBalance(u.getUserId(), accNum);
													log.info("deposit successful, new balance: " + balance);
												}
											} catch (BusinessException e) {
												log.warn(e.getMessage());
											} catch (NumberFormatException e) {
												log.warn("choice should be number only");
											}
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									} catch (NumberFormatException e) {
										log.warn("choice should be number only");
									}
								}
							} catch (NumberFormatException e) {
								log.warn("choice should be number only");
							}
							break;
						case 5:
							// make money transfer
							log.info("enter the number of the account from which you wish to transfer funds");
							try {
								accNum = Integer.parseInt(scanner.nextLine());
								balance = customerService.checkBalance(u.getUserId(), accNum);
								log.info("current balance of your account number " + accNum + ": " + balance);
							} catch (NumberFormatException e) {
								log.warn("choice should be number only");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
							}
							log.info("enter amount to transfer");
							try {
								// sql rollback
								int r = 0;
								double transferAmt = Integer.parseInt(scanner.nextLine());
								if (transferAmt > balance) {
									log.warn("invalid entry, account does not have sufficient funds");
								} else if (transferAmt < 0) {
									log.warn("invalid entry, cannot transfer negative funds");
								} else {
									log.info("enter email of customer you wish to transfer money");
									String reciever = scanner.nextLine();
									try {
										r = userService.customerExists(reciever);
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
									if (r == 0) {
										log.warn("customer with email '" + reciever + "' not located");
									} else {
										log.info(
												"customer found, please enter account number you wish to deposit money into");
										int rNum = Integer.parseInt(scanner.nextLine());
										try {
											if (accountService.accountExists(r, rNum)) {
												double remainder = balance - transferAmt;
												try {
													if (transactionService.Transfer(u.getUserId(), accNum, r, rNum,
															transferAmt) == 1) {
														try {
															if (accountService.updateAccountBalance(accNum,
																	remainder) == 1) {
																double deposit = customerService.checkBalance(r, rNum);
																deposit += transferAmt;
																try {
																	if (accountService.updateAccountBalance(rNum,
																			deposit) == 1) {
																		log.info("transfer successful");
																	}
																} catch (BusinessException e) {
																	log.warn(e.getMessage());
																}
															}
														} catch (BusinessException e) {
															log.warn(e.getMessage());
														}
													}
												} catch (BusinessException e) {
													log.warn(e.getMessage());
												}
											} else {
												log.info("customer account " + rNum + " not located");
											}
										} catch (BusinessException e) {
											log.warn(e.getMessage());
										}
									}
								}
							} catch (NumberFormatException e) {
								log.warn("choice should be number only");
							}
							break;
						case 6:
							// return to main menu
							log.info("returning to main menu");
							break;
						default:
							// invalid option
							log.warn("invalid");
							break;
						}
					} while (cu != 6);
				} else if (u.getUserType().matches("e")) {// if user == employee
					EmployeeService employeeService = new EmployeeServiceImpl();
					int em = 0;
					do {
						log.info("Employee Options Menu");
						log.info("-----------------------");
						log.info("1) view account applications");
						log.info("2) view customer accounts");
						log.info("3) view transaction log");
						log.info("4) return to main menu");
						log.info("please enter an appropriate option(1-4)");
						try {
							em = Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {
							log.warn("choice should be number only");
						}
						int choice = 0;
						// get input
						switch (em) {
						case 1:
							// approve or reject account applications
							List<UnapprovedAccount> unapprovedAccountList = new ArrayList<>();
							do {
								try {
									unapprovedAccountList = employeeService.viewUnapprovedAccounts();
									log.info("there are " + unapprovedAccountList.size() + " unapproved account(s)");
									for (int i = 0; i < unapprovedAccountList.size(); i++) {
										log.info((i + 1) + ")" + unapprovedAccountList.get(i).getApplicantName());
									}

									log.info(unapprovedAccountList.size() + 1 + ")return to employee menu");
									log.info("enter choice of application to view or exit");
									try {
										choice = Integer.parseInt(scanner.nextLine());
										if (choice > 0 && choice <= unapprovedAccountList.size() + 1) {
											if (choice == unapprovedAccountList.size() + 1) {
												log.info("returning to employee menu");
											} else {
												int approve = 0;
												do {
													UnapprovedAccount accountChosen = unapprovedAccountList
															.get(choice - 1);
													log.info(accountChosen);
													log.info(
															"enter 1 to approve, 2 to reject, 3 to return to employee menu");
													try {
														approve = Integer.parseInt(scanner.nextLine());
														if (approve == 1) {// application approved
															if (accountService.updateAccountStatus(
																	accountChosen.getAccountNum(), true,
																	u.getUserId()) == 1) {
																log.info("account approved");
																break;
															}
														} else if (approve == 2) {// application rejected
															if (accountService.rejectAccount(
																	accountChosen.getAccountNum()) == 1) {
																log.info("account rejected");
																break;
															}
														} else if (approve == 3) {
															log.info("returning to employee menu");
															break;
														} else {
															log.warn("invalid choice");
														}
													} catch (NumberFormatException e) {
														log.warn("choice should be number only");
													}
												} while (approve != 3);
											}
										} else {
											log.warn("invalid choice");
										}
									} catch (NumberFormatException e) {
										log.warn("choice should be number only");
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								} catch (NumberFormatException e) {
									log.warn("choice should be number only");
								}
								// break;
							} while (choice != unapprovedAccountList.size() + 1);
							break;
						case 2:
							// view customer's accounts
							List<User> customerList = new ArrayList<>();
							do {
								try {
									customerList = employeeService.viewAllAccounts();
									log.info("there are " + customerList.size() + " registered customer(s)");
									for (int i = 0; i < customerList.size(); i++) {
										log.info((i + 1) + ")" + customerList.get(i).getName());
									}
									log.info(customerList.size() + 1 + ")return to employee menu");
									log.info("enter choice of customer to view accounts or exit");
									try {
										choice = Integer.parseInt(scanner.nextLine());
										if (choice > 0 && choice <= customerList.size() + 1) {
											if (choice == customerList.size() + 1) {
												log.info("returning to employee menu");
											} else {
												User customerChosen = customerList.get(choice - 1);
												List<Account> customerAccounts = employeeService
														.viewAccountByCustomerId(customerChosen.getUserId());
												for (Account a : customerAccounts) {
													log.info("account number : " + a.getAccountNum()
															+ ", account balance : " + a.getBalance()
															+ ", date opened : " + a.getOpened());
												}
											}
										} else {
											log.warn("invalid choice");
										}
									} catch (NumberFormatException e) {
										log.warn("choice should be number only");
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
							} while (choice != customerList.size() + 1);
							break;
						case 3:
							// view transaction log
							List<Transaction> transactionList = new ArrayList<>();
							try {
								transactionList = employeeService.pullAllTransactionData();
								log.info("Printing all transactions");
								for (Transaction t : transactionList) {
									log.info(t);
								}
							} catch (BusinessException e) {
								log.warn(e.getMessage());
							}
							break;
						case 4:
							// return to main menu
							log.info("returning to main");
							break;
						default:
							// invalid option
							log.warn("invalid choice");
							break;
						}
					} while (em != 4);
				} else {
					log.error("system error");
				}
				break;
			case 3:
				log.info("Goodbye");
				break;
			default:
				// invalid entry
				log.warn("invalid choice");
				break;
			}
		} while (ch != 3);
	}
}
