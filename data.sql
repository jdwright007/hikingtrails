/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  John


 

drop table if exists hiking_Trail;

create table Hiking_Trail ( 
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    distance DOUBLE NOT NULL,
    difficulty VARCHAR(30)
);


insert into Hiking_Trail (name, distance, difficulty) values
    ('Middle Fork', 5.0, 'Easy'),
    ('Long Valley', 12.0, 'Moderate');


*/