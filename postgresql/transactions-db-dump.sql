--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2024-02-27 12:04:04

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
-- TOC entry 210 (class 1259 OID 219444)
-- Name: scheduled_transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.scheduled_transactions (
    id bigint NOT NULL,
    client_account_id bigint NOT NULL,
    due_date date NOT NULL,
    status character varying(255) NOT NULL,
    transaction_type character varying(255) NOT NULL,
    amount real NOT NULL,
    fee real NOT NULL
);


ALTER TABLE public.scheduled_transactions OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 219443)
-- Name: scheduled_transactions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.scheduled_transactions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.scheduled_transactions_id_seq OWNER TO postgres;

--
-- TOC entry 3313 (class 0 OID 0)
-- Dependencies: 209
-- Name: scheduled_transactions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.scheduled_transactions_id_seq OWNED BY public.scheduled_transactions.id;


--
-- TOC entry 3164 (class 2604 OID 219447)
-- Name: scheduled_transactions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scheduled_transactions ALTER COLUMN id SET DEFAULT nextval('public.scheduled_transactions_id_seq'::regclass);


--
-- TOC entry 3307 (class 0 OID 219444)
-- Dependencies: 210
-- Data for Name: scheduled_transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.scheduled_transactions (id, client_account_id, due_date, status, transaction_type, amount, fee) FROM stdin;
5	1	2024-02-26	Pending	Deposit	1000	92.1
6	1	2024-02-25	Pending	Deposit	1000	33
7	1	2024-03-25	Pending	Transfer	10000	690
8	1	2024-03-26	Pending	Transfer	10000	690
9	1	2024-03-30	Pending	Transfer	10000	470
10	1	2024-04-22	Pending	Transfer	10000	170
11	1	2024-02-26	Executed	Transfer	0.1	3.003
\.


--
-- TOC entry 3314 (class 0 OID 0)
-- Dependencies: 209
-- Name: scheduled_transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.scheduled_transactions_id_seq', 11, true);


--
-- TOC entry 3166 (class 2606 OID 219451)
-- Name: scheduled_transactions scheduled_transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scheduled_transactions
    ADD CONSTRAINT scheduled_transactions_pkey PRIMARY KEY (id);


-- Completed on 2024-02-27 12:04:04

--
-- PostgreSQL database dump complete
--

