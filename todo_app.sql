-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 1.0.4
-- PostgreSQL version: 15.0
-- Project Site: pgmodeler.io
-- Model Author: ---
-- -- object: pg_database_owner | type: ROLE --
-- -- DROP ROLE IF EXISTS pg_database_owner;
-- CREATE ROLE pg_database_owner WITH 
-- 	INHERIT
-- 	 PASSWORD '********';
-- -- ddl-end --
-- 

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: todo_app | type: DATABASE --
-- DROP DATABASE IF EXISTS todo_app;
CREATE DATABASE todo_app
	ENCODING = 'UTF8'
	LC_COLLATE = 'en_US.UTF-8'
	LC_CTYPE = 'en_US.UTF-8'
	TABLESPACE = pg_default
	OWNER = postgres;
-- ddl-end --


-- object: public.todo | type: TABLE --
-- DROP TABLE IF EXISTS public.todo CASCADE;
CREATE TABLE public.todo (
	id integer NOT NULL,
	title character varying(200) NOT NULL,
	description text,
	deadline timestamp,
	priority integer NOT NULL,
	done boolean,
	CONSTRAINT "priorityCheck" CHECK (((priority >= 0) AND (priority <= 10))),
	CONSTRAINT todo_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE public.todo OWNER TO postgres;
-- ddl-end --

-- object: "grant_CU_26541e8cda" | type: PERMISSION --
GRANT CREATE,USAGE
   ON SCHEMA public
   TO pg_database_owner;
-- ddl-end --

-- object: "grant_U_cd8e46e7b6" | type: PERMISSION --
GRANT USAGE
   ON SCHEMA public
   TO PUBLIC;
-- ddl-end --


