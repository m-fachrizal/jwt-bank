--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-04-06 10:47:17

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 51107)
-- Name: account_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_details (
    account_no integer NOT NULL,
    available_balance double precision
);


ALTER TABLE public.account_details OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 51106)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 51112)
-- Name: transaction_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction_details (
    id integer NOT NULL,
    account_no integer,
    reference_number integer,
    transaction_amount double precision,
    transaction_flag character varying(1)
);


ALTER TABLE public.transaction_details OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 51117)
-- Name: user_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_details (
    user_id character varying(255) NOT NULL,
    account_no integer,
    password character varying(255)
);


ALTER TABLE public.user_details OWNER TO postgres;

--
-- TOC entry 3318 (class 0 OID 51107)
-- Dependencies: 210
-- Data for Name: account_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account_details (account_no, available_balance) FROM stdin;
1234567890	3000
2123456789	1000
\.


--
-- TOC entry 3319 (class 0 OID 51112)
-- Dependencies: 211
-- Data for Name: transaction_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction_details (id, account_no, reference_number, transaction_amount, transaction_flag) FROM stdin;
1	1234567890	2063518016	1000	C
2	2123456789	2063518016	1000	D
\.


--
-- TOC entry 3320 (class 0 OID 51117)
-- Dependencies: 212
-- Data for Name: user_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_details (user_id, account_no, password) FROM stdin;
ABC12345	1234567890	$2a$10$Kv0qFZp2.eFD2wA4GtskyO7/Y1tEysJseVRD4879k1kCELlLitYlO
ABC12346	2123456789	$2a$10$3VJhJdAqOl3FhfYewf1CsOuosy8uqfQq/FoDnUocjsatQ4bH5NBbS
\.


--
-- TOC entry 3326 (class 0 OID 0)
-- Dependencies: 209
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 2, true);


--
-- TOC entry 3173 (class 2606 OID 51111)
-- Name: account_details account_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_details
    ADD CONSTRAINT account_details_pkey PRIMARY KEY (account_no);


--
-- TOC entry 3175 (class 2606 OID 51116)
-- Name: transaction_details transaction_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction_details
    ADD CONSTRAINT transaction_details_pkey PRIMARY KEY (id);


--
-- TOC entry 3177 (class 2606 OID 51123)
-- Name: user_details user_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_details
    ADD CONSTRAINT user_details_pkey PRIMARY KEY (user_id);


-- Completed on 2022-04-06 10:47:17

--
-- PostgreSQL database dump complete
--

