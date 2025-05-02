USE unicate;

SELECT uniID INTO @usc_id FROM universities WHERE universities.UniversityName = 'University of Southern California';
INSERT INTO Activities (actName, actLocation, actImage, uniID)
VALUES 
(
	'Natural History Museum of Los Angeles County',
    '900 Exposition Blvd, Los Angeles, CA',
    'https://cdn.prod.website-files.com/660332e04a42ee42011d9a2b/660332e04a42ee42011d9e3e_120222.1.jpg',
    @usc_id
),
(
	'California African American Museum',
    '600 State Dr, Los Angeles, CA',
    'https://caamuseum.org/content/1-home/caam_home_slide_03.jpg',
    @usc_id
),
(
    'California Science Center',
    '700 Exposition Park Dr, Los Angeles, CA',
    'https://drupal-prod.visitcalifornia.com/sites/default/files/styles/fluid_1920/public/2021-09/VC_California-Science-Center_Exterior_SUPPLIED_1280x640.jpg.webp?itok=5j4JnXiV',
    @usc_id
);

SELECT uniID INTO @uchicago_id FROM universities WHERE universities.UniversityName = 'University of Chicago';
INSERT INTO Activities (actName, actLocation, actImage, uniID)
VALUES 
(
	'Jackson Park',
    '6401 S Stony Island Ave, Chicago, IL',
    'https://upload.wikimedia.org/wikipedia/commons/4/4e/Museum_of_Science_and_Industry.jpg',
    @uchicago_id
),
(
	'Hyde Park Art Center',
    '5020 S Cornell Ave, Chicago, IL',
    'https://assets.simpleviewinc.com/simpleview/image/upload/c_fit,w_1800,h_800/crm/chicago/A.Alexander_HydePark_030_8b5fdafc-5056-a36f-23ccf685c9837c4d.jpg',
    @uchicago_id
),
(
	'Museum of Science and Industry',
    '5700 S DuSable Lake Shore Dr, Chicago, IL',
    'https://designa.com/hs-fs/hubfs/Bild%20Website%20Chicago.jpg?width=2000&name=Bild%20Website%20Chicago.jpg',
    @uchicago_id
);

SELECT uniID INTO @uta_id FROM universities WHERE universities.UniversityName = 'University of Texas at Austin';
INSERT INTO Activities (actName, actLocation, actImage, uniID)
VALUES 
(
	'Texas State Capitol',
    '1100 Congress Ave., Austin, TX',
    'https://upload.wikimedia.org/wikipedia/commons/d/df/TexasStateCapitol-2010-01.JPG',
    @uta_id
),
(
	'Lady Bird Lake',
    '1820 S Lakeshore Blvd, Austin, TX',
    'https://upload.wikimedia.org/wikipedia/commons/0/09/AustinSkylineLouNeffPoint-Jun2010-a.JPG',
    @uta_id
),
(
	'Blanton Museum of Art',
    '200 E Martin Luther King Jr Blvd, Austin, TX',
    'https://blantonmuseum.b-cdn.net/app/uploads/2024/11/Visitors-on-the-Blantons-new-grounds-_-Photo-by-Sloan-Breeden-Photography-1.jpg',
    @uta_id
);

SELECT uniID INTO @psu_id FROM universities WHERE universities.UniversityName = 'Pennsylvania State University';
INSERT INTO Activities (actName, actLocation, actImage, uniID)
VALUES 
(
	'Palmer Museum of Art',
    '650 Bigler Rd, University Park, PA',
    'https://palmermuseum.psu.edu/wp-content/uploads/2024/07/Screenshot-2024-07-22-at-11.46.51%E2%80%AFAM.png',
    @psu_id
),
(
	'The Arboretum',
    'E Park Ave &, Bigler Rd, State College, PA',
    'https://www.outreach.psu.edu/wp-content/uploads/271392653_316647270468267_1423326135730153567_n-e1645819535666.jpg',
    @psu_id
),
(
	'Pegula Ice Arena',
    '250 University Dr, State College, PA',
    'https://static01.nyt.com/images/2013/10/13/sports/dog-pennstate1/dog-pennstate1-superJumbo.jpg',
    @psu_id
);

SELECT uniID INTO @ucla_id FROM universities WHERE universities.UniversityName = 'UCLA';
INSERT INTO Activities (actName, actLocation, actImage, uniID)
VALUES 
(
	'Bruin Theater',
    '948 Broxton Ave, Los Angeles, CA',
    'https://www.laconservancy.org/wp-content/uploads/2014/07/Bruin_Theatre_Westwood_Los_Angeles_CA__at_night.jpg',
    @ucla_id
),
(
	'Griffith Observatory',
    '2800 E Observatory Rd, Los Angeles, CA',
    'https://griffithobservatory.org/wp-content/uploads/2021/03/cameron-venti-PiqHSHYO3Uw-unsplash_noCautionTape_web-1600x800-1638571193.jpg',
    @ucla_id
),
(
	'Fowler Museum',
    '308 Charles E Young Dr N, Los Angeles, CA',
    'https://upload.wikimedia.org/wikipedia/commons/4/41/Fowler.JPG',
    @ucla_id
);