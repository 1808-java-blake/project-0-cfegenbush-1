CREATE TABLE Users (
    Username VARCHAR(20) NOT NULL PRIMARY KEY,
    Password VARCHAR(20) NOT NULL,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(20) NOT NULL,
    Admin   BINARY NOT NULL
);

CREATE TABLE Accounts (
    AccountNumber SERIAL PRIMARY KEY,
    AccountType VARCHAR(10) NOT NULL,
    Balance NUMERIC(10,2),
    CreationDate TIMESTAMP
);

CREATE TABLE Transactions (
    TransactionID SERIAL PRIMARY KEY,
    TransactionType VARCHAR(20) NOT NULL,
    Amount NUMERIC(10,2) NOT NULL,
    Date TIMESTAMP NOT NULL,
    AccountTransferredTo INTEGER,
    AccountID INTEGER REFERENCES Account(AccountNumber)
);

CREATE TABLE AccountOwners (
    Username VARCHAR(20) REFERENCES Users(Username),
    AccountID INTEGER REFERENCES Account(AccountNumber)
    PRIMARY KEY(Username, AccountID) 
);