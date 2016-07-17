CREATE TABLE Patients
(
	fullname 		VARCHAR NOT NULL,
	gender 			VARCHAR,
	clientType		VARCHAR NOT NULL,
	accomodation		VARCHAR,
	partnership		VARCHAR NOT NULL,
	age			SMALLINT,
	stayDuration		SMALLINT,
	accomodationClientID	BIGINT NOT NULL,
	personIDNum		BIGINT,
	dateOfArrival		DATE NOT NULL,
	dateOfDeparture		DATE NOT NULL,
	PRIMARY KEY(personIdNum)
);

CREATE Table Procedures
(
	name			VARCHAR NOT NULL,
	department		VARCHAR,
	capacity		SMALLINT NOT NULL,
	duration 		SMALLINT,
	startTime		TIME NOT NULL,
	endTime			TIME NOT NULL,
	PRIMARY KEY(name)
);
INSERT INTO procedures VALUES('TEST2', 'testini', 3, 10, TIME '05:55', TIME '06:05');

CREATE TABLE Appointments
(
	appointmentID		SERIAL,
	patientIDNum		BIGINT,
	procedureName		VARCHAR,
	appointmentDate		DATE NOT NULL,
	startTime		TIME NOT NULL,
	endTime			TIME NOT NULL,
	PRIMARY KEY(appointmentID),
	FOREIGN KEY(patientIDNum) REFERENCES Patients(personIDNum),
	FOREIGN KEY(procedureName) REFERENCES Procedures(name)
);