USE unicate;

SELECT uniID INTO @usc_id FROM universities WHERE universities.UniversityName = 'University of Southern California';
INSERT INTO Activities (actName, uniID)
VALUES 
(
	'Natural History Museum of Los Angeles County',
    @usc_id
),
(
	'California African American Museum',
    @usc_id
),
(
	'California Science Center',
    @usc_id
);

SELECT uniID INTO @uchicago_id FROM universities WHERE universities.UniversityName = 'University of Chicago';
INSERT INTO Activities (actName, uniID)
VALUES 
(
	'Jackson Park',
    @uchicago_id
),
(
	'Hyde Park Art Center',
    @uchicago_id
),
(
	'Museum of Science and Industry',
    @uchicago_id
);

SELECT uniID INTO @uta_id FROM universities WHERE universities.UniversityName = 'University of Texas at Austin';
INSERT INTO Activities (actName, uniID)
VALUES 
(
	'Texas State Capitol',
    @uta_id
),
(
	'Lady Bird Lake',
    @uta_id
),
(
	'Blanton Museum of Art',
    @uta_id
);

SELECT uniID INTO @psu_id FROM universities WHERE universities.UniversityName = 'Pennsylvania State University';
INSERT INTO Activities (actName, uniID)
VALUES 
(
	'Palmer Museum of Art',
    @psu_id
),
(
	'The Arboretum',
    @psu_id
),
(
	'Pegula Ice Arena',
    @psu_id
);

SELECT uniID INTO @ucla_id FROM universities WHERE universities.UniversityName = 'UCLA';
INSERT INTO Activities (actName, uniID)
VALUES 
(
	'Bruin Theater',
    @ucla_id
),
(
	'Griffith Park',
    @ucla_id
),
(
	'Fowler Museum',
    @ucla_id
);