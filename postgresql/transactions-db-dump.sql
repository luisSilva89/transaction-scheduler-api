--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2024-02-28 10:54:03

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
-- TOC entry 3312 (class 0 OID 0)
-- Dependencies: 209
-- Name: scheduled_transactions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.scheduled_transactions_id_seq OWNED BY public.scheduled_transactions.id;


--
-- TOC entry 3163 (class 2604 OID 219447)
-- Name: scheduled_transactions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scheduled_transactions ALTER COLUMN id SET DEFAULT nextval('public.scheduled_transactions_id_seq'::regclass);


--
-- TOC entry 3306 (class 0 OID 219444)
-- Dependencies: 210
-- Data for Name: scheduled_transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.scheduled_transactions (id, client_account_id, due_date, status, transaction_type, amount, fee) FROM stdin;
8	1	2024-03-26	Pending	Transfer	10000	690
9	1	2024-03-30	Pending	Transfer	10000	470
10	1	2024-04-22	Pending	Transfer	10000	170
5	1	2024-02-27	Executed	Transfer	500	18
7	1	2024-03-29	Pending	Transfer	10000	470
12	1	2024-02-28	Executed	Transfer	1	3.03
13	1	2024-02-28	Executed	Transfer	1000	33
14	1	2024-02-29	Pending	Transfer	1001	90.09
15	1	2024-02-29	Pending	Transfer	2000	180
16	1	2024-03-09	Pending	Transfer	2000	180
17	1	2024-03-10	Pending	Transfer	2001	164.082
18	1	2024-03-19	Pending	Transfer	2001	164.082
19	1	2024-03-20	Pending	Transfer	2001	138.069
20	1	2024-03-29	Pending	Transfer	2001	138.069
21	1	2024-03-30	Pending	Transfer	2001	94.047
22	1	2024-04-08	Pending	Transfer	2001	94.047
23	1	2024-04-09	Pending	Transfer	2001	34.017
24	1	2024-12-09	Pending	Transfer	2001	34.017
\.


--
-- TOC entry 3313 (class 0 OID 0)
-- Dependencies: 209
-- Name: scheduled_transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.scheduled_transactions_id_seq', 24, true);


--
-- TOC entry 3165 (class 2606 OID 219451)
-- Name: scheduled_transactions scheduled_transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scheduled_transactions
    ADD CONSTRAINT scheduled_transactions_pkey PRIMARY KEY (id);


-- Completed on 2024-02-28 10:54:03

--
-- PostgreSQL database dump complete
--

