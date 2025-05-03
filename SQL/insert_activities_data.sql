USE unicate;

-- Descriptions of activities are taken from Wikipedia.

SELECT uniID INTO @usc_id FROM universities WHERE universities.UniversityName = 'University of Southern California';
INSERT INTO Activities (actName, actLocation, actImage, description, uniID)
VALUES 
(
	'Natural History Museum of Los Angeles County',
    '900 Exposition Blvd, Los Angeles, CA',
    'https://cdn.prod.website-files.com/660332e04a42ee42011d9a2b/660332e04a42ee42011d9e3e_120222.1.jpg',
    '"The Natural History Museum of Los Angeles County is the largest natural and historical museum in the Western United States. The museum is located in Exposition Park, Los Angeles, next to the California Science Center." - Wikipedia',
    @usc_id
),
(
	'California African American Museum',
    '600 State Dr, Los Angeles, CA',
    'https://caamuseum.org/content/1-home/caam_home_slide_03.jpg',
    '"The California African American Museum is a museum located in Exposition Park, Los Angeles, next to the California Science Center. The museum focuses on enrichment and education on the cultural heritage and history of African Americans with a focus on California and western United States." - Wikipedia',
    @usc_id
),
(
    'California Science Center',
    '700 Exposition Park Dr, Los Angeles, CA',
    'https://drupal-prod.visitcalifornia.com/sites/default/files/styles/fluid_1920/public/2021-09/VC_California-Science-Center_Exterior_SUPPLIED_1280x640.jpg.webp?itok=5j4JnXiV',
    '"The California Science Center is a state agency and science museum located in Exposition Park, Los Angeles, next to the Natural History Museum of Los Angeles County and the University of Southern California." - Wikipedia',
    @usc_id
);

SELECT uniID INTO @uchicago_id FROM universities WHERE universities.UniversityName = 'University of Chicago';
INSERT INTO Activities (actName, actLocation, actImage, description, uniID)
VALUES 
(
	'Jackson Park',
    '6401 S Stony Island Ave, Chicago, IL',
    'https://upload.wikimedia.org/wikipedia/commons/4/4e/Museum_of_Science_and_Industry.jpg',
    'Jackson Park is a 551.5-acre (223.2 ha) urban park on the shore of Lake Michigan on the South Side of Chicago. Straddling the Hyde Park, Woodlawn, and South Shore neighborhoods, the park was designed in 1871 by Frederick Law Olmsted and Calvert Vaux and remodeled in 1893 to serve as the site of the World''s Columbian Exposition. It is one of the largest and most historically significant parks in the city, and many of the park''s features are mementos of the fair—including the Garden of the Phoenix, the Statue of The Republic, and the Museum of Science and Industry. - Wikipedia',
    @uchicago_id
),
(
	'Hyde Park Art Center',
    '5020 S Cornell Ave, Chicago, IL',
    'https://assets.simpleviewinc.com/simpleview/image/upload/c_fit,w_1800,h_800/crm/chicago/A.Alexander_HydePark_030_8b5fdafc-5056-a36f-23ccf685c9837c4d.jpg',
    '"The Hyde Park Art Center is a visual arts organization and the oldest alternative exhibition space in the city of Chicago. Since 2006, HPAC has been located just north of Hyde Park Boulevard, at 5020 S.Cornell Avenue, in the Kenwood neighborhood of Chicago, Illinois." - Wikipedia',
    @uchicago_id
),
(
	'Museum of Science and Industry',
    '5700 S DuSable Lake Shore Dr, Chicago, IL',
    'https://designa.com/hs-fs/hubfs/Bild%20Website%20Chicago.jpg?width=2000&name=Bild%20Website%20Chicago.jpg',
    '"The Griffin Museum of Science and Industry, officially the Kenneth C. Griffin Museum of Science and Industry, is a science museum located in Chicago, Illinois, in Jackson Park, in the Hyde Park neighborhood between Lake Michigan and The University of Chicago." - Wikipedia',
    @uchicago_id
);

SELECT uniID INTO @uta_id FROM universities WHERE universities.UniversityName = 'University of Texas at Austin';
INSERT INTO Activities (actName, actLocation, actImage, description, uniID)
VALUES 
(
	'Texas State Capitol',
    '1100 Congress Ave., Austin, TX',
    'https://upload.wikimedia.org/wikipedia/commons/d/df/TexasStateCapitol-2010-01.JPG',
    '"The Texas State Capitol is the capitol and seat of government of the U.S. state of Texas. Located in downtown Austin, Texas, the structure houses the offices and chambers of the Texas Legislature and of the Governor of Texas." - Wikipedia',
    @uta_id
),
(
	'Lady Bird Lake',
    '1820 S Lakeshore Blvd, Austin, TX',
    'https://upload.wikimedia.org/wikipedia/commons/0/09/AustinSkylineLouNeffPoint-Jun2010-a.JPG',
    '"Lady Bird Lake is a river-like reservoir on the Colorado River in Austin, Texas, United States. The City of Austin created the reservoir in 1960 as a cooling pond for a new city power plant. The lake, which has a surface area of 416 acres, is now used primarily for recreation and flood control." - Wikipedia',
    @uta_id
),
(
	'Blanton Museum of Art',
    '200 E Martin Luther King Jr Blvd, Austin, TX',
    'https://blantonmuseum.b-cdn.net/app/uploads/2024/11/Visitors-on-the-Blantons-new-grounds-_-Photo-by-Sloan-Breeden-Photography-1.jpg',
    '"The Jack S. Blanton Museum of Art at the University of Texas at Austin is one of the largest university art museums in the U.S. with 189,340 square feet devoted to temporary exhibitions, permanent collection galleries, storage, administrative offices, classrooms, a print study room, an auditorium, shop, and cafe." - Wikipedia',
    @uta_id
);

SELECT uniID INTO @psu_id FROM universities WHERE universities.UniversityName = 'Pennsylvania State University';
INSERT INTO Activities (actName, actLocation, actImage, description, uniID)
VALUES 
(
	'Palmer Museum of Art',
    '650 Bigler Rd, University Park, PA',
    'https://palmermuseum.psu.edu/wp-content/uploads/2024/07/Screenshot-2024-07-22-at-11.46.51%E2%80%AFAM.png',
    '"The Palmer Museum of Art is the art museum of Pennsylvania State University, located on the University Park campus in State College, Pennsylvania." - Wikipedia',
    @psu_id
),
(
	'The Arboretum',
    'E Park Ave &, Bigler Rd, State College, PA',
    'https://www.outreach.psu.edu/wp-content/uploads/271392653_316647270468267_1423326135730153567_n-e1645819535666.jpg',
    '"The Arboretum at Penn State, which contains the H.O. Smith Botanic Gardens, is a new arboretum at Pennsylvania State University adjacent to its University Park campus in State College, Pennsylvania. It is Penn State''s second arboretum, joining the Arboretum at Penn State Behrend, which was created in 2003." - Wikipedia',
    @psu_id
),
(
	'Pegula Ice Arena',
    '250 University Dr, State College, PA',
    'https://static01.nyt.com/images/2013/10/13/sports/dog-pennstate1/dog-pennstate1-superJumbo.jpg',
    '"The Pegula Ice Arena is a 6,014-seat multi-purpose arena in University Park, Pennsylvania on the campus of Penn State University. The facility is located on the corner of Curtin Road and University Drive near the Bryce Jordan Center." - Wikipedia',
    @psu_id
);

SELECT uniID INTO @ucla_id FROM universities WHERE universities.UniversityName = 'UCLA';
INSERT INTO Activities (actName, actLocation, actImage, description, uniID)
VALUES 
(
	'Bruin Theater',
    '948 Broxton Ave, Los Angeles, CA',
    'https://www.laconservancy.org/wp-content/uploads/2014/07/Bruin_Theatre_Westwood_Los_Angeles_CA__at_night.jpg',
    '"The Bruin Theater, also known as the Regency Bruin Theater or Fox Bruin Theater, is a 670-seat movie palace located in the Westwood neighborhood of Los Angeles, California, near University of California, Los Angeles." - Wikipedia',
    @ucla_id
),
(
	'Griffith Observatory',
    '2800 E Observatory Rd, Los Angeles, CA',
    'https://griffithobservatory.org/wp-content/uploads/2021/03/cameron-venti-PiqHSHYO3Uw-unsplash_noCautionTape_web-1600x800-1638571193.jpg',
    '"Griffith Observatory is an observatory in Los Angeles, California, on the south-facing slope of Mount Hollywood in Griffith Park. It commands a view of the Los Angeles Basin including Downtown Los Angeles to the southeast, Hollywood to the south, and the Pacific Ocean to the southwest." - Wikipedia',
    @ucla_id
),
(
	'Fowler Museum',
    '308 Charles E Young Dr N, Los Angeles, CA',
    'https://upload.wikimedia.org/wikipedia/commons/4/41/Fowler.JPG',
    '"The Fowler Museum at UCLA is a museum on the campus of the University of California, Los Angeles which explores art and material culture primarily from Africa, Asia and the Pacific, and the Americas, past and present." - Wikipedia',
    @ucla_id
);