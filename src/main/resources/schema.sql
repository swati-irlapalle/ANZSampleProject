
DROP TABLE IF EXISTS transactionDetails;
DROP TABLE IF EXISTS accountDetails;
CREATE TABLE accountDetails (
  accountNumber INT PRIMARY KEY,
  accountName VARCHAR(50) NOT NULL,
  accountType VARCHAR(50) NOT NULL,
  balanceDate DATE NOT NULL,
  currency VARCHAR(3)  NOT NULL,
  openingAvailableBalance DOUBLE  NOT NULL
);


CREATE TABLE transactionDetails(
  transactionId INT PRIMARY KEY,
  currency VARCHAR(3)  NOT NULL,
  amount DOUBLE,
  debitOrCredit VARCHAR(10) NOT NULL,
  account_id INT NOT NULL,
  valueDate DATE NOT NULL,
  transactionNarrative VARCHAR(10) NULL,
  FOREIGN KEY (account_id) REFERENCES accountDetails(accountNumber)
);



