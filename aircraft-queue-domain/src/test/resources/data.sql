----------------------------------------------------------------------------------------
-- AIRCRAFT_TYPE
----------------------------------------------------------------------------------------
insert into AIRCRAFT_TYPE (
	ID, TYPE_NAME, TYPE_VALUE, DESCRIPTION, ACTIVE
)
values (
	'100', 'CARGO', '100', '2018-08-23', 'Y'
);
insert into AIRCRAFT_TYPE (
	ID, TYPE_NAME, TYPE_VALUE, DESCRIPTION, ACTIVE
)
values (
	'200', 'PASSENGER', '200', '2018-08-23', 'Y'
);
insert into AIRCRAFT_TYPE (
	ID, TYPE_NAME, TYPE_VALUE, DESCRIPTION, ACTIVE
)
values (
	'300', 'VIP', '300', '2018-08-23', 'Y'
);
insert into AIRCRAFT_TYPE (
	ID, TYPE_NAME, TYPE_VALUE, DESCRIPTION, ACTIVE
)
values (
	'400', 'EMERGENCY', '400', '2018-08-23', 'Y'
);

----------------------------------------------------------------------------------------
-- AIRCRAFT_SIZE
----------------------------------------------------------------------------------------
insert into AIRCRAFT_SIZE (
	ID, SIZE_NAME, SIZE_VALUE, DESCRIPTION, ACTIVE
)
values (
	'100', 'SMALL', '100', '2018-08-23', 'Y'
);
insert into AIRCRAFT_SIZE (
	ID, SIZE_NAME, SIZE_VALUE, DESCRIPTION, ACTIVE
)
values (
	'200', 'LARGE', '200', '2018-08-23', 'Y'
);

----------------------------------------------------------------------------------------
-- ENQUEUED_AIRCRAFT
----------------------------------------------------------------------------------------
insert into ENQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'100', '400', '200', CURRENT_TIMESTAMP(), 'Emergency, Large, 100'
);
insert into ENQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'200', '400', '100', CURRENT_TIMESTAMP(), 'Emergency, Small, 200'
);

insert into ENQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'300', '300', '200', CURRENT_TIMESTAMP(), 'VIP Large 300'
);
insert into ENQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'400', '300', '100', CURRENT_TIMESTAMP(), 'VIP Small 400'
);

insert into ENQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'500', '200', '200', CURRENT_TIMESTAMP(), 'Passenger Large 500'
);
insert into ENQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'600', '200', '100', CURRENT_TIMESTAMP(), 'Passenger Small 600'
);

insert into ENQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'700', '100', '200', CURRENT_TIMESTAMP(), 'Cargo Large 700'
);
insert into ENQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'800', '100', '100', CURRENT_TIMESTAMP(), 'Cargo Small 800'
);

----------------------------------------------------------------------------------------
-- DEQUEUED_AIRCRAFT
----------------------------------------------------------------------------------------
insert into DEQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, DEQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'100', '400', '200', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 200, 'Emergency, Large, 100'
);
insert into DEQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, DEQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'200', '400', '100', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 200, 'Emergency, Small, 200'
);

insert into DEQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, DEQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'300', '300', '200', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 300, 'VIP Large 300'
);
insert into DEQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, DEQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'400', '300', '100', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 400, 'VIP Small 400'
);

insert into DEQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, DEQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'500', '200', '200', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 500, 'Passenger Large 500'
);
insert into DEQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, DEQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'600', '200', '100', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 600, 'Passenger Small 600'
);

insert into DEQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, DEQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'700', '100', '200', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 700, 'Cargo Large 700'
);
insert into DEQUEUED_AIRCRAFT (
	ID, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP, DEQUEUED_TIMESTAMP, AIRCRAFT_NAME
)
values (
	'800', '100', '100', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 800, 'Cargo Small 800'
);

