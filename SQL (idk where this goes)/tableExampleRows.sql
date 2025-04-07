-- User table
CREATE TABLE Users (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    HashedPassword VARCHAR(255) NOT NULL
);

-- University table
CREATE TABLE Universities (
    uniID INT AUTO_INCREMENT PRIMARY KEY,
    UniversityName VARCHAR(100) NOT NULL,
    CalendarDates TEXT  -- You might want to normalize this further or store as JSON, depending on usage
);

-- User/University table (normalized many-to-many relationship)
CREATE TABLE UserUniversity (
    userID INT,
    uniID INT,
    PRIMARY KEY (userID, uniID),
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (uniID) REFERENCES Universities(uniID)
);

-- User Entry/Activity table (like a post table)
CREATE TABLE UserActivity (
    locID INT AUTO_INCREMENT PRIMARY KEY,
    PostTitle VARCHAR(100) NOT NULL,
    userID INT,
    FOREIGN KEY (userID) REFERENCES Users(userID)
);

-- Comments table
CREATE TABLE Comments (
    commentID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    content TEXT NOT NULL,
    locID INT,
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (locID) REFERENCES UserActivity(locID)
);

-- Insert example users
INSERT INTO Users (Username, HashedPassword)
VALUES 
('alice123', 'hashed_pass_1'),
('bob456', 'hashed_pass_2');

-- Insert example universities
INSERT INTO Universities (UniversityName, CalendarDates)
VALUES 
('USC', '2025-01-13, 2025-03-17, 2025-05-08'),
('UCLA', '2025-01-06, 2025-03-24, 2025-06-12');

-- Link users to universities
INSERT INTO UserUniversity (userID, uniID)
VALUES 
(1, 1),
(2, 2);

-- Insert example user activities
INSERT INTO UserActivity (PostTitle, userID)
VALUES 
('Spring Semester Tips', 1),
('Finals Week Hacks', 2);

-- Insert example comments
INSERT INTO Comments (userID, content, locID)
VALUES 
(2, 'Thanks for the advice!', 1),
(1, 'Nice location!', 2);
