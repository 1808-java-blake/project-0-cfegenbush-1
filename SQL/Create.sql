CREATE TABLE Users (
    Username VARCHAR(20) NOT NULL PRIMARY KEY,
    Password VARCHAR(20) NOT NULL,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(20) NOT NULL,
    Admin INTEGER NOT NULL
);

CREATE TABLE Accounts (
    AccountNumber INTEGER PRIMARY KEY,
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
    AccountID INTEGER REFERENCES Accounts(AccountNumber) ON DELETE CASCADE
);

CREATE TABLE AccountOwners (
    Username VARCHAR(20) REFERENCES Users(Username) ON DELETE CASCADE,
    AccountID INTEGER REFERENCES Accounts(AccountNumber) ON DELETE CASCADE,
    PRIMARY KEY(Username, AccountID) 
);