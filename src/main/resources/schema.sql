CREATE TABLE Claim_Types(
	Claim_Types_Id int,
    Type varchar(20),
	CONSTRAINT PK_Claim_Types PRIMARY KEY (Claim_Types_Id)
);

CREATE TABLE Claims(
	Claims_Id int AUTO_INCREMENT,
    Policy_Id int,
    User_Name varchar(10),
    Subscription_Id int,
    Claim_Date date,
    Claim_Types_Id int,
    Claim_Summary varchar(100),
    Claim_Details varchar(500),
    Claim_Status varchar(12),
    Is_Raised_By_Policy_Holder bit,
    Claimant_Full_Name varchar(100),
    Claimant_Date_Of_Birth date,
    Claimant_Address varchar(100),
    Claimant_ID_Proof_Type varchar(20),
    Claimant_ID_Proof_Number varchar(20),
    Response_ETA date,
    CONSTRAINT PK_Claims PRIMARY KEY (Claims_Id),
    CONSTRAINT FK_Claims_Types_Claims FOREIGN KEY (Claim_Types_Id) REFERENCES Claim_Types(Claim_Types_Id)
);

CREATE TABLE Claim_Responses(
	Claim_Responses_Id int AUTO_INCREMENT,
    Response_Date date,
    Response_Details varchar(500),
    Claims_id int,
    CONSTRAINT PK_Claim_Responses PRIMARY KEY (Claim_Responses_id),
    CONSTRAINT FK_Claims_Claim_Responses FOREIGN KEY (Claims_Id) REFERENCES Claims(Claims_Id)
);

CREATE TABLE Users(
    User_Name varchar(40),
    password varchar(40) not null,
    Role varchar(40) not null,
    Is_Account_Locked boolean
);