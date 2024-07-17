create table accommodation(
    id integer,
    type varchar(32),
    bedtype varchar(32),
    maxguest integer,
    description varchar(512),
    primary key("id")
)
create table room_fair(
                        id integer,
						value double precision,
                        season varchar(32),
                        primary key("id")
)
create table accommodation_room_fair_relation(
                        id integer,
						accommodation_id integer,
						room_fair_id integer,
						foreign key(accommodation_id) references accommodation (id),
						foreign key(room_fair_id) references room_fair(id)
)

select* from accommodation;
select* from room_fair;
select* from accommodation_room_fair_relation;


SELECT accommodation.type, room_fair.value
FROM accommodation_room_fair_relation
JOIN accommodation
ON accommodation_room_fair_relation.id = accommodation.id
JOIN room_fair
ON room_fair.id = accommodation_room_fair_relation.id



