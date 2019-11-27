DROP TABLE IF EXISTS public.roles CASCADE;
CREATE TABLE public.roles (
	id SERIAL PRIMARY KEY,
	description CHARACTER VARYING(255) NOT NULL,
	role_name CHARACTER VARYING(255) NOT NULL,

	UNIQUE(role_name)
);

DROP TABLE IF EXISTS public.users CASCADE;
CREATE TABLE public.users (
	id SERIAL PRIMARY KEY,
	first_name CHARACTER VARYING(255) NOT NULL,
	last_name CHARACTER VARYING(255) NOT NULL,
	patronymic CHARACTER VARYING(255) NOT NULL,

	UNIQUE(first_name, last_name, patronymic)
);

-- The unique combination (user_id, role_id) is necessary because admin can be doctor, doctor can be patients, and the user object can be inherited
-- in a combination of data when creating a role_patient object from ROLE_DOCTOR or ROLE_DOCTOR from ROLE_ADMIN, then when logging in with a user login
-- dynamically defines its role and read user data, as well as the user object on the required table
DROP TABLE IF EXISTS public.role_user CASCADE;
CREATE TABLE public.role_user (
	user_id INTEGER NOT NULL,
	role_id INTEGER NOT NULL,
	login CHARACTER VARYING(255) NOT NULL,
	password CHARACTER VARYING(255) NOT NULL,

	UNIQUE(user_id, role_id),
	UNIQUE(login),
	FOREIGN KEY (user_id) REFERENCES public.users (id),
	FOREIGN KEY (role_id) REFERENCES public.roles (id)
);

DROP TABLE IF EXISTS public.admins CASCADE;
CREATE TABLE public.admins (
	user_id INTEGER NOT NULL,
	description CHARACTER VARYING(255) NOT NULL,

	UNIQUE(user_id),
	FOREIGN KEY (user_id) REFERENCES public.users (id)
);

DROP TABLE IF EXISTS public.doctors CASCADE;
CREATE TABLE public.doctors (
	user_id INTEGER NOT NULL,
	position CHARACTER VARYING(255) NOT NULL,
	education CHARACTER VARYING(255) NOT NULL,

	UNIQUE(user_id),
	FOREIGN KEY (user_id) REFERENCES public.users (id)
);

DROP TABLE IF EXISTS public.patients CASCADE;
CREATE TABLE public.patients (
	user_id INTEGER NOT NULL,
	address CHARACTER VARYING(255) NOT NULL,
	phone CHARACTER VARYING(255) NOT NULL,
	polus CHARACTER VARYING(255) NOT NULL,

	UNIQUE(user_id),
	FOREIGN KEY (user_id) REFERENCES public.users (id)
);
-- create table public.create_admin, FUNCTION func_create_admin and TRIGGER trig_create_admin
DROP TABLE IF EXISTS public.create_admin CASCADE;
CREATE TABLE public.create_admin (
	-- public.users
	first_name CHARACTER VARYING(255) NOT NULL,
	last_name CHARACTER VARYING(255) NOT NULL,
	patronymic CHARACTER VARYING(255) NOT NULL,
	-- public.admins
	description CHARACTER VARYING(255) NOT NULL,
	-- public.role_user
	login CHARACTER VARYING(255) NOT NULL,
	password CHARACTER VARYING(255) NOT NULL
);

DROP TRIGGER IF EXISTS trig_create_admin ON public.create_admin;
DROP FUNCTION IF EXISTS func_create_admin();

CREATE OR REPLACE FUNCTION func_create_admin()
RETURNS trigger AS
$$
	BEGIN
 		INSERT INTO public.users(first_name, last_name, patronymic) VALUES
 		(NEW.first_name, NEW.last_name, NEW.patronymic);

 		INSERT INTO public.role_user(user_id, role_id, login, password) VALUES (
 			(SELECT id FROM public.users WHERE first_name = NEW.first_name AND last_name = NEW.last_name AND patronymic = NEW.patronymic),
 			(SELECT id FROM public.roles WHERE role_name = 'ROLE_ADMIN'),
 			NEW.login,
 			NEW.password
 		);

		INSERT INTO public.admins(user_id, description) VALUES (
			(SELECT id FROM public.users WHERE first_name = NEW.first_name AND last_name = NEW.last_name AND patronymic = NEW.patronymic),
			NEW.description
		);

		DELETE FROM public.create_admin;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trig_create_admin
AFTER INSERT ON public.create_admin FOR EACH ROW
EXECUTE PROCEDURE func_create_admin();

-- create table public.create_doctor, FUNCTION func_create_doctor and TRIGGER trig_create_doctor
DROP TABLE IF EXISTS public.create_doctor CASCADE;
CREATE TABLE public.create_doctor (
	-- public.users
	first_name CHARACTER VARYING(255) NOT NULL,
	last_name CHARACTER VARYING(255) NOT NULL,
	patronymic CHARACTER VARYING(255) NOT NULL,
	-- public.doctors
	position CHARACTER VARYING(255) NOT NULL,
	education CHARACTER VARYING(255) NOT NULL,
	-- public.role_user
	login CHARACTER VARYING(255) NOT NULL,
	password CHARACTER VARYING(255) NOT NULL
);

DROP TRIGGER IF EXISTS trig_create_doctor ON public.create_doctor;
DROP FUNCTION IF EXISTS func_create_doctor();

CREATE OR REPLACE FUNCTION func_create_doctor()
RETURNS trigger AS
$$
	BEGIN
		INSERT INTO public.users(first_name, last_name, patronymic) VALUES
		(NEW.first_name, NEW.last_name, NEW.patronymic);

		INSERT INTO public.role_user(user_id, role_id, login, password) VALUES (
			(SELECT id FROM public.users WHERE first_name = NEW.first_name AND last_name = NEW.last_name AND patronymic = NEW.patronymic),
			(SELECT id FROM public.roles WHERE role_name = 'ROLE_DOCTOR'),
			NEW.login,
			NEW.password
		);

		INSERT INTO public.doctors(user_id, position, education) VALUES (
			(SELECT id FROM public.users WHERE first_name = NEW.first_name AND last_name = NEW.last_name AND patronymic = NEW.patronymic),
			NEW.position,
			NEW.education
		);

		DELETE FROM public.create_doctor;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trig_create_doctor
AFTER INSERT ON public.create_doctor FOR EACH ROW
EXECUTE PROCEDURE func_create_doctor();

-- create table public.create_patient, FUNCTION func_create_patient and TRIGGER trig_create_patient
DROP TABLE IF EXISTS public.create_patient CASCADE;
CREATE TABLE public.create_patient (
	-- public.users
	first_name CHARACTER VARYING(255) NOT NULL,
	last_name CHARACTER VARYING(255) NOT NULL,
	patronymic CHARACTER VARYING(255) NOT NULL,
	-- public.patients
	address CHARACTER VARYING(255) NOT NULL,
	phone CHARACTER VARYING(255) NOT NULL,
	polus CHARACTER VARYING(255) NOT NULL,
	-- public.role_user
	login CHARACTER VARYING(255) NOT NULL,
	password CHARACTER VARYING(255) NOT NULL
);

DROP TRIGGER IF EXISTS trig_create_patient ON public.create_patient;
DROP FUNCTION IF EXISTS func_create_patient();

CREATE OR REPLACE FUNCTION func_create_patient()
RETURNS trigger AS
$$
	BEGIN
		INSERT INTO public.users(first_name, last_name, patronymic) VALUES
		(NEW.first_name, NEW.last_name, NEW.patronymic);

		INSERT INTO public.role_user(user_id, role_id, login, password) VALUES (
			(SELECT id FROM public.users WHERE first_name = NEW.first_name AND last_name = NEW.last_name AND patronymic = NEW.patronymic),
			(SELECT id FROM public.roles WHERE role_name = 'ROLE_PATIENT'),
			NEW.login,
			NEW.password
		);

		INSERT INTO public.patients(user_id, address, phone, polus) VALUES (
			(SELECT id FROM public.users WHERE first_name = NEW.first_name AND last_name = NEW.last_name AND patronymic = NEW.patronymic),
			NEW.address,
			NEW.phone,
			NEW.polus
		);

		DELETE FROM public.create_patient;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trig_create_patient
AFTER INSERT ON public.create_patient FOR EACH ROW
EXECUTE PROCEDURE func_create_patient();

-- create table public.create_admin_user_by_id, FUNCTION func_create_admin_user_by_id and TRIGGER trig_create_admin_user_by_id
DROP TABLE IF EXISTS public.create_admin_user_by_id CASCADE;
CREATE TABLE public.create_admin_user_by_id (
	-- public.users
	user_id INTEGER NOT NULL,
	-- public.admins
	description CHARACTER VARYING(255) NOT NULL,
	-- public.role_user
	login CHARACTER VARYING(255) NOT NULL,
	password CHARACTER VARYING(255) NOT NULL
);

DROP TRIGGER IF EXISTS trig_create_admin_user_by_id ON public.create_admin_user_by_id;
DROP FUNCTION IF EXISTS func_create_admin_user_by_id();

CREATE OR REPLACE FUNCTION func_create_admin_user_by_id()
RETURNS trigger AS
$$
	BEGIN
		INSERT INTO public.role_user(user_id, role_id, login, password) VALUES (
			NEW.user_id,
			(SELECT id FROM public.roles WHERE role_name = 'ROLE_ADMIN'),
			NEW.login,
			NEW.password
		);

		INSERT INTO public.admins(user_id, description) VALUES (
			NEW.user_id,
			NEW.description
		);

		DELETE FROM public.create_admin_user_by_id;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trig_create_admin_user_by_id
AFTER INSERT ON public.create_admin_user_by_id FOR EACH ROW
EXECUTE PROCEDURE func_create_admin_user_by_id();

-- create table public.create_doctor_user_by_id, FUNCTION func_create_doctor_user_by_id and TRIGGER trig_create_doctor_user_by_id
DROP TABLE IF EXISTS public.create_doctor_user_by_id CASCADE;
CREATE TABLE public.create_doctor_user_by_id (
	-- public.users
	user_id INTEGER NOT NULL,
	-- public.doctors
	position CHARACTER VARYING(255) NOT NULL,
	education CHARACTER VARYING(255) NOT NULL,
	-- public.role_user
	login CHARACTER VARYING(255) NOT NULL,
	password CHARACTER VARYING(255) NOT NULL
);

DROP TRIGGER IF EXISTS trig_create_doctor_user_by_id ON public.create_doctor_user_by_id;
DROP FUNCTION IF EXISTS func_create_doctor_user_by_id();

CREATE OR REPLACE FUNCTION func_create_doctor_user_by_id()
RETURNS trigger AS
$$
	BEGIN
		INSERT INTO public.role_user(user_id, role_id, login, password) VALUES (
			NEW.user_id,
			(SELECT id FROM public.roles WHERE role_name = 'ROLE_DOCTOR'),
			NEW.login,
			NEW.password
		);

		INSERT INTO public.doctors(user_id, position, education) VALUES (
			NEW.user_id,
			NEW.position,
			NEW.education
		);

		DELETE FROM public.create_doctor_user_by_id;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trig_create_doctor_user_by_id
AFTER INSERT ON public.create_doctor_user_by_id FOR EACH ROW
EXECUTE PROCEDURE func_create_doctor_user_by_id();

-- create table public.create_patient_user_by_id, FUNCTION func_create_patient_user_by_id and TRIGGER trig_create_patient_user_by_id
DROP TABLE IF EXISTS public.create_patient_user_by_id CASCADE;
CREATE TABLE public.create_patient_user_by_id (
	-- public.users
	user_id INTEGER NOT NULL,
	-- public.patients
	address CHARACTER VARYING(255) NOT NULL,
	phone CHARACTER VARYING(255) NOT NULL,
	polus CHARACTER VARYING(255) NOT NULL,
	-- public.role_user
	login CHARACTER VARYING(255) NOT NULL,
	password CHARACTER VARYING(255) NOT NULL
);

DROP TRIGGER IF EXISTS trig_create_patient_user_by_id ON public.create_patient_user_by_id;
DROP FUNCTION IF EXISTS func_create_patient_user_by_id();

CREATE OR REPLACE FUNCTION func_create_patient_user_by_id()
RETURNS trigger AS
$$
	BEGIN
		INSERT INTO public.role_user(user_id, role_id, login, password) VALUES (
			NEW.user_id,
			(SELECT id FROM public.roles WHERE role_name = 'ROLE_PATIENT'),
			NEW.login,
			NEW.password
		);

		INSERT INTO public.patients(user_id, address, phone, polus) VALUES (
			NEW.user_id,
			NEW.address,
			NEW.phone,
			NEW.polus
		);

		DELETE FROM public.create_patient_user_by_id;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trig_create_patient_user_by_id
AFTER INSERT ON public.create_patient_user_by_id FOR EACH ROW
EXECUTE PROCEDURE func_create_patient_user_by_id();

-- create table public.delet_login, FUNCTION func_delet_login and TRIGGER trig_delet_login
DROP TABLE IF EXISTS public.delet_login CASCADE;
CREATE TABLE public.delet_login (
	login CHARACTER VARYING(255) NOT NULL
);

DROP TRIGGER IF EXISTS trig_delet_login ON public.delet_login;
DROP FUNCTION IF EXISTS func_delet_login();

CREATE OR REPLACE FUNCTION func_delet_login()
RETURNS trigger AS
$$
	DECLARE
		_roleName text;
		_user_id INTEGER;
		_count_user_id INTEGER;
	BEGIN
		_roleName = (SELECT r.role_name FROM public.role_user ru, public.roles r WHERE r.id=ru.role_id AND ru.login=NEW.login);
		_user_id = (SELECT ru.user_id FROM public.role_user ru, public.roles r WHERE r.id=ru.role_id AND ru.login=NEW.login);

		CASE
			WHEN _roleName='ROLE_ADMIN' THEN
				DELETE FROM public.admins a WHERE a.user_id = _user_id;
				DELETE FROM public.role_user ru WHERE ru.login = NEW.login;

			WHEN _roleName='ROLE_DOCTOR' THEN
				DELETE FROM public.doctors a WHERE a.user_id = _user_id;
				DELETE FROM public.role_user ru WHERE ru.login = NEW.login;

			WHEN _roleName='ROLE_PATIENT' THEN
				DELETE FROM public.patients a WHERE a.user_id = _user_id;
				DELETE FROM public.role_user ru WHERE ru.login = NEW.login;

			ELSE

		END CASE;

		_count_user_id = (SELECT count(ru.user_id) FROM public.role_user ru, public.roles r WHERE r.id=ru.role_id AND ru.user_id=_user_id);
		IF _count_user_id = 0 THEN
			DELETE FROM public.users u WHERE u.id = _user_id;
		END IF;

		DELETE FROM public.delet_login;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trig_delet_login
AFTER INSERT ON public.delet_login FOR EACH ROW
EXECUTE PROCEDURE func_delet_login();

-- insert main data
INSERT INTO public.roles(description, role_name) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO public.roles(description, role_name) VALUES ('Doctor', 'ROLE_DOCTOR');
INSERT INTO public.roles(description, role_name) VALUES ('Patient', 'ROLE_PATIENT');

-- test insert
INSERT INTO public.create_admin(first_name, last_name, patronymic, description, login, password) VALUES ('Ivan', 'Ivanov', 'Ivanovich', 'senior admin', 'admin1', '2waesdfe');
INSERT INTO public.create_admin(first_name, last_name, patronymic, description, login, password) VALUES ('Petr', 'Petrov', 'Petrovich', 'junior  admin', 'admin2', '2dhgjhdb');
INSERT INTO public.create_doctor(first_name, last_name, patronymic, position, education, login, password) VALUES ('Dima', 'Krapecki', 'Vladimirovich', 'therapist', 'highest' , 'docFedya', '3azdthnc');
INSERT INTO public.create_patient(first_name, last_name, patronymic, address, phone, polus, login, password) VALUES ('Sergei', 'Martinov', 'Andreevich', 'Central highway, 6', '7453165413', 'p254653', 'patMart', '6tatzshxd');
INSERT INTO public.create_patient(first_name, last_name, patronymic, address, phone, polus, login, password) VALUES ('Andrei', 'Fetisov', 'Aleksandrovich', 'Sea street, 2', '6841236842', 'a648765', 'patSom', '3Fgferdd');
INSERT INTO public.create_doctor(first_name, last_name, patronymic, position, education, login, password) VALUES ('Boris', 'Matveev', 'Viktorovich', 'surgeon', 'higher unfinished' , 'docVasya', '5Sfdgghf');
INSERT INTO public.create_patient(first_name, last_name, patronymic, address, phone, polus, login, password) VALUES ('Victor', 'Kryagev', 'Sergeevich', 'Flower street, 2', '9852369854', 'k9784651', 'patMark', '9szdfghs');
INSERT INTO public.create_admin_user_by_id(user_id, description, login, password) VALUES ('3', 'new admin', 'admin3', '2tryfjgkhjb');
INSERT INTO public.create_doctor_user_by_id(user_id, position, education, login, password) VALUES ('1', 'oculist', 'average', 'byId_1_ToDoc', '2tryfjgkhjb');
INSERT INTO public.create_patient_user_by_id(user_id, address, phone, polus, login, password) VALUES ('1', 'Sea street, 12', '81575595', 'p148963256', 'byId_1_ToPat', '2tryfjgkhjb');

SELECT ru.user_id, r.role_name, ru.login FROM public.role_user ru, public.roles r WHERE r.id=ru.role_id ORDER BY ru.user_id, r.role_name;