CREATE DATABASE IF NOT EXISTS unicate;
USE unicate;

-- in case the fields change or something with the sql changes
-- DROP TABLE IF EXISTS Users;
-- DROP TABLE IF EXISTS Universities;
-- DROP TABLE IF EXISTS UserUniversity;
-- DROP TABLE IF EXISTS UserActivity;commentscommentsuniversities
-- DROP TABLE IF EXISTS Comments;

CREATE TABLE Users (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    HashedPassword VARCHAR(255) NOT NULL
);


CREATE TABLE Universities (
    uniID INT AUTO_INCREMENT PRIMARY KEY,
    UniversityName VARCHAR(100) NOT NULL,
    CalendarDates TEXT  
);

-- i don't know if we need data classes like these; i'm now just referring to the data classes part of the doc
-- from here:
CREATE TABLE DateRange (
    dateID INT AUTO_INCREMENT PRIMARY KEY,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL
);

CREATE TABLE uniDates (
    dateID INT,
    uniID INT,
    PRIMARY KEY (dateID, uniID),
    FOREIGN KEY (dateID) REFERENCES DateRange(dateID),
    FOREIGN KEY (uniID) REFERENCES Universities(uniID)
);

CREATE TABLE UserDates (
    dateID INT,
    userID INT,
    PRIMARY KEY (dateID, userID),
    FOREIGN KEY (dateID) REFERENCES DateRange(dateID),
    FOREIGN KEY (userID) REFERENCES Users(userID)
);
-- to here

CREATE TABLE UserUniversity (
    userID INT,
    uniID INT,
    PRIMARY KEY (userID, uniID),
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (uniID) REFERENCES Universities(uniID)
);


CREATE TABLE UserActivity (
    actID INT AUTO_INCREMENT PRIMARY KEY,
    postName VARCHAR(100) NOT NULL,
    userID INT,
    FOREIGN KEY (userID) REFERENCES Users(userID),
    avgRating FLOAT DEFAULT 0
);


CREATE TABLE Comments (
    commentID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    actRating FLOAT DEFAULT 0,
    bodyText TEXT NOT NULL,
    actID INT,
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (actID) REFERENCES UserActivity(actID)
);