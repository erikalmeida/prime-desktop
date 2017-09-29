--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

-- Started on 2017-03-22 10:14:45 VET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12433)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3524 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 96008)
-- Name: accesorio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE accesorio (
    id bigint NOT NULL,
    descripcion character varying(255),
    estatus character varying(255),
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE accesorio OWNER TO postgres;

--
-- TOC entry 272 (class 1259 OID 97191)
-- Name: accesorio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE accesorio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE accesorio_id_seq OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 96016)
-- Name: accion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE accion (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    nombre character varying(255) NOT NULL
);


ALTER TABLE accion OWNER TO postgres;

--
-- TOC entry 273 (class 1259 OID 97193)
-- Name: accion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE accion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE accion_id_seq OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 96026)
-- Name: aceite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aceite (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idgrosoraceite bigint NOT NULL,
    idtipoaceite bigint NOT NULL
);


ALTER TABLE aceite OWNER TO postgres;

--
-- TOC entry 274 (class 1259 OID 97195)
-- Name: aceite_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE aceite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE aceite_id_seq OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 96034)
-- Name: agenda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE agenda (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE agenda OWNER TO postgres;

--
-- TOC entry 275 (class 1259 OID 97197)
-- Name: agenda_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE agenda_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE agenda_id_seq OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 96039)
-- Name: anomalia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE anomalia (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE anomalia OWNER TO postgres;

--
-- TOC entry 276 (class 1259 OID 97199)
-- Name: anomalia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE anomalia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anomalia_id_seq OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 96049)
-- Name: asunto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE asunto (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    nombre character varying(255) NOT NULL
);


ALTER TABLE asunto OWNER TO postgres;

--
-- TOC entry 277 (class 1259 OID 97201)
-- Name: asunto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE asunto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE asunto_id_seq OWNER TO postgres;

--
-- TOC entry 367 (class 1259 OID 97401)
-- Name: auth_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE auth_group (
    id integer NOT NULL,
    name character varying(80) NOT NULL
);


ALTER TABLE auth_group OWNER TO postgres;

--
-- TOC entry 366 (class 1259 OID 97399)
-- Name: auth_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auth_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auth_group_id_seq OWNER TO postgres;

--
-- TOC entry 3525 (class 0 OID 0)
-- Dependencies: 366
-- Name: auth_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE auth_group_id_seq OWNED BY auth_group.id;


--
-- TOC entry 369 (class 1259 OID 97411)
-- Name: auth_group_permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE auth_group_permissions (
    id integer NOT NULL,
    group_id integer NOT NULL,
    permission_id integer NOT NULL
);


ALTER TABLE auth_group_permissions OWNER TO postgres;

--
-- TOC entry 368 (class 1259 OID 97409)
-- Name: auth_group_permissions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auth_group_permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auth_group_permissions_id_seq OWNER TO postgres;

--
-- TOC entry 3526 (class 0 OID 0)
-- Dependencies: 368
-- Name: auth_group_permissions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE auth_group_permissions_id_seq OWNED BY auth_group_permissions.id;


--
-- TOC entry 365 (class 1259 OID 97393)
-- Name: auth_permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE auth_permission (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    content_type_id integer NOT NULL,
    codename character varying(100) NOT NULL
);


ALTER TABLE auth_permission OWNER TO postgres;

--
-- TOC entry 364 (class 1259 OID 97391)
-- Name: auth_permission_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auth_permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auth_permission_id_seq OWNER TO postgres;

--
-- TOC entry 3527 (class 0 OID 0)
-- Dependencies: 364
-- Name: auth_permission_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE auth_permission_id_seq OWNED BY auth_permission.id;


--
-- TOC entry 371 (class 1259 OID 97419)
-- Name: auth_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE auth_user (
    id integer NOT NULL,
    password character varying(128) NOT NULL,
    last_login timestamp with time zone,
    is_superuser boolean NOT NULL,
    username character varying(150) NOT NULL,
    first_name character varying(30) NOT NULL,
    last_name character varying(30) NOT NULL,
    email character varying(254) NOT NULL,
    is_staff boolean NOT NULL,
    is_active boolean NOT NULL,
    date_joined timestamp with time zone NOT NULL
);


ALTER TABLE auth_user OWNER TO postgres;

--
-- TOC entry 373 (class 1259 OID 97429)
-- Name: auth_user_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE auth_user_groups (
    id integer NOT NULL,
    user_id integer NOT NULL,
    group_id integer NOT NULL
);


ALTER TABLE auth_user_groups OWNER TO postgres;

--
-- TOC entry 372 (class 1259 OID 97427)
-- Name: auth_user_groups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auth_user_groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auth_user_groups_id_seq OWNER TO postgres;

--
-- TOC entry 3528 (class 0 OID 0)
-- Dependencies: 372
-- Name: auth_user_groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE auth_user_groups_id_seq OWNED BY auth_user_groups.id;


--
-- TOC entry 370 (class 1259 OID 97417)
-- Name: auth_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auth_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auth_user_id_seq OWNER TO postgres;

--
-- TOC entry 3529 (class 0 OID 0)
-- Dependencies: 370
-- Name: auth_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE auth_user_id_seq OWNED BY auth_user.id;


--
-- TOC entry 375 (class 1259 OID 97437)
-- Name: auth_user_user_permissions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE auth_user_user_permissions (
    id integer NOT NULL,
    user_id integer NOT NULL,
    permission_id integer NOT NULL
);


ALTER TABLE auth_user_user_permissions OWNER TO postgres;

--
-- TOC entry 374 (class 1259 OID 97435)
-- Name: auth_user_user_permissions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auth_user_user_permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auth_user_user_permissions_id_seq OWNER TO postgres;

--
-- TOC entry 3530 (class 0 OID 0)
-- Dependencies: 374
-- Name: auth_user_user_permissions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE auth_user_user_permissions_id_seq OWNED BY auth_user_user_permissions.id;


--
-- TOC entry 187 (class 1259 OID 96059)
-- Name: caja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE caja (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idtipocaja bigint NOT NULL
);


ALTER TABLE caja OWNER TO postgres;

--
-- TOC entry 278 (class 1259 OID 97203)
-- Name: caja_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE caja_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE caja_id_seq OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 96067)
-- Name: calificacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE calificacion (
    id bigint NOT NULL,
    calificacionatencion integer NOT NULL,
    calificacioninstalacion integer NOT NULL,
    calificacionservicio integer NOT NULL,
    comentario character varying(255),
    estatus character varying(255),
    fecha timestamp without time zone,
    idordenentrega bigint NOT NULL
);


ALTER TABLE calificacion OWNER TO postgres;

--
-- TOC entry 279 (class 1259 OID 97205)
-- Name: calificacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE calificacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE calificacion_id_seq OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 96075)
-- Name: cita; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cita (
    id bigint NOT NULL,
    descripcion character varying(255),
    estado character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fecha timestamp without time zone NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    nombrereferido character varying(255),
    idespacio bigint,
    ideventualidad bigint,
    idmotivo bigint,
    idpromocion bigint,
    idservicio bigint,
    idvehiculo bigint NOT NULL
);


ALTER TABLE cita OWNER TO postgres;

--
-- TOC entry 280 (class 1259 OID 97207)
-- Name: cita_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cita_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cita_id_seq OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 96083)
-- Name: ciudad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ciudad (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idestado bigint NOT NULL
);


ALTER TABLE ciudad OWNER TO postgres;

--
-- TOC entry 281 (class 1259 OID 97209)
-- Name: ciudad_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ciudad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ciudad_id_seq OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 96091)
-- Name: clase; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE clase (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idtipoclase bigint NOT NULL
);


ALTER TABLE clase OWNER TO postgres;

--
-- TOC entry 282 (class 1259 OID 97211)
-- Name: clase_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE clase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clase_id_seq OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 96099)
-- Name: color; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE color (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE color OWNER TO postgres;

--
-- TOC entry 283 (class 1259 OID 97213)
-- Name: color_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE color_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE color_id_seq OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 96109)
-- Name: combustible; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE combustible (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idtipocombustible bigint NOT NULL
);


ALTER TABLE combustible OWNER TO postgres;

--
-- TOC entry 284 (class 1259 OID 97215)
-- Name: combustible_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE combustible_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE combustible_id_seq OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 96117)
-- Name: configuracionservicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE configuracionservicio (
    id bigint NOT NULL,
    visible boolean NOT NULL,
    idservicio bigint NOT NULL
);


ALTER TABLE configuracionservicio OWNER TO postgres;

--
-- TOC entry 285 (class 1259 OID 97217)
-- Name: configuracionservicio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE configuracionservicio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE configuracionservicio_id_seq OWNER TO postgres;

--
-- TOC entry 381 (class 1259 OID 97541)
-- Name: constance_config; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE constance_config (
    id integer NOT NULL,
    key character varying(255) NOT NULL,
    value text NOT NULL
);


ALTER TABLE constance_config OWNER TO postgres;

--
-- TOC entry 380 (class 1259 OID 97539)
-- Name: constance_config_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE constance_config_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE constance_config_id_seq OWNER TO postgres;

--
-- TOC entry 3531 (class 0 OID 0)
-- Dependencies: 380
-- Name: constance_config_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE constance_config_id_seq OWNED BY constance_config.id;


--
-- TOC entry 379 (class 1259 OID 97528)
-- Name: dashboard_userdashboardmodule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE dashboard_userdashboardmodule (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    module character varying(255) NOT NULL,
    app_label character varying(255),
    "user" integer NOT NULL,
    "column" integer NOT NULL,
    "order" integer NOT NULL,
    settings text NOT NULL,
    children text NOT NULL,
    collapsed boolean NOT NULL,
    CONSTRAINT dashboard_userdashboardmodule_column_check CHECK (("column" >= 0)),
    CONSTRAINT dashboard_userdashboardmodule_user_check CHECK (("user" >= 0))
);


ALTER TABLE dashboard_userdashboardmodule OWNER TO postgres;

--
-- TOC entry 378 (class 1259 OID 97526)
-- Name: dashboard_userdashboardmodule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE dashboard_userdashboardmodule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dashboard_userdashboardmodule_id_seq OWNER TO postgres;

--
-- TOC entry 3532 (class 0 OID 0)
-- Dependencies: 378
-- Name: dashboard_userdashboardmodule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE dashboard_userdashboardmodule_id_seq OWNED BY dashboard_userdashboardmodule.id;


--
-- TOC entry 195 (class 1259 OID 96122)
-- Name: deporte; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE deporte (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE deporte OWNER TO postgres;

--
-- TOC entry 286 (class 1259 OID 97219)
-- Name: deporte_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE deporte_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE deporte_id_seq OWNER TO postgres;

--
-- TOC entry 377 (class 1259 OID 97497)
-- Name: django_admin_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE django_admin_log (
    id integer NOT NULL,
    action_time timestamp with time zone NOT NULL,
    object_id text,
    object_repr character varying(200) NOT NULL,
    action_flag smallint NOT NULL,
    change_message text NOT NULL,
    content_type_id integer,
    user_id integer NOT NULL,
    CONSTRAINT django_admin_log_action_flag_check CHECK ((action_flag >= 0))
);


ALTER TABLE django_admin_log OWNER TO postgres;

--
-- TOC entry 376 (class 1259 OID 97495)
-- Name: django_admin_log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE django_admin_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE django_admin_log_id_seq OWNER TO postgres;

--
-- TOC entry 3533 (class 0 OID 0)
-- Dependencies: 376
-- Name: django_admin_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE django_admin_log_id_seq OWNED BY django_admin_log.id;


--
-- TOC entry 363 (class 1259 OID 97383)
-- Name: django_content_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE django_content_type (
    id integer NOT NULL,
    app_label character varying(100) NOT NULL,
    model character varying(100) NOT NULL
);


ALTER TABLE django_content_type OWNER TO postgres;

--
-- TOC entry 362 (class 1259 OID 97381)
-- Name: django_content_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE django_content_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE django_content_type_id_seq OWNER TO postgres;

--
-- TOC entry 3534 (class 0 OID 0)
-- Dependencies: 362
-- Name: django_content_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE django_content_type_id_seq OWNED BY django_content_type.id;


--
-- TOC entry 361 (class 1259 OID 97372)
-- Name: django_migrations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE django_migrations (
    id integer NOT NULL,
    app character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    applied timestamp with time zone NOT NULL
);


ALTER TABLE django_migrations OWNER TO postgres;

--
-- TOC entry 360 (class 1259 OID 97370)
-- Name: django_migrations_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE django_migrations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE django_migrations_id_seq OWNER TO postgres;

--
-- TOC entry 3535 (class 0 OID 0)
-- Dependencies: 360
-- Name: django_migrations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE django_migrations_id_seq OWNED BY django_migrations.id;


--
-- TOC entry 387 (class 1259 OID 97597)
-- Name: django_session; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE django_session (
    session_key character varying(40) NOT NULL,
    session_data text NOT NULL,
    expire_date timestamp with time zone NOT NULL
);


ALTER TABLE django_session OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 96132)
-- Name: espacio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE espacio (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estado boolean NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    identificacion character varying(255),
    idagenda bigint
);


ALTER TABLE espacio OWNER TO postgres;

--
-- TOC entry 288 (class 1259 OID 97223)
-- Name: espacio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE espacio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE espacio_id_seq OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 96140)
-- Name: espacioherramienta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE espacioherramienta (
    id bigint NOT NULL,
    estatus character varying(255),
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idespacio bigint,
    idherramienta bigint
);


ALTER TABLE espacioherramienta OWNER TO postgres;

--
-- TOC entry 289 (class 1259 OID 97225)
-- Name: espacioherramienta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE espacioherramienta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE espacioherramienta_id_seq OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 96145)
-- Name: espaciomecanico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE espaciomecanico (
    id bigint NOT NULL,
    estatus character varying(255),
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idespacio bigint,
    idmecanico bigint
);


ALTER TABLE espaciomecanico OWNER TO postgres;

--
-- TOC entry 290 (class 1259 OID 97227)
-- Name: espaciomecanico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE espaciomecanico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE espaciomecanico_id_seq OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 96150)
-- Name: espacioordenservicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE espacioordenservicio (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion date,
    fechamodificacion date,
    idespacio bigint
);


ALTER TABLE espacioordenservicio OWNER TO postgres;

--
-- TOC entry 287 (class 1259 OID 97221)
-- Name: espacioordenservicio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE espacioordenservicio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE espacioordenservicio_id_seq OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 96155)
-- Name: espaciotecnologia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE espaciotecnologia (
    id bigint NOT NULL,
    estatus character varying(255),
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idespacio bigint,
    idtecnologia bigint
);


ALTER TABLE espaciotecnologia OWNER TO postgres;

--
-- TOC entry 291 (class 1259 OID 97229)
-- Name: espaciotecnologia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE espaciotecnologia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE espaciotecnologia_id_seq OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 96160)
-- Name: estado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE estado (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    tiempoestimadoejecucion timestamp without time zone,
    tiempomaximoejecucion timestamp without time zone
);


ALTER TABLE estado OWNER TO postgres;

--
-- TOC entry 292 (class 1259 OID 97231)
-- Name: estado_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE estado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE estado_id_seq OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 96168)
-- Name: etapa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE etapa (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    tiempoestimadoejecucion timestamp without time zone,
    tiempomaximoejecucion timestamp without time zone
);


ALTER TABLE etapa OWNER TO postgres;

--
-- TOC entry 293 (class 1259 OID 97233)
-- Name: etapa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE etapa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE etapa_id_seq OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 96176)
-- Name: eventualidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE eventualidad (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idtipoeventualidad bigint
);


ALTER TABLE eventualidad OWNER TO postgres;

--
-- TOC entry 294 (class 1259 OID 97235)
-- Name: eventualidad_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE eventualidad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE eventualidad_id_seq OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 96184)
-- Name: falla; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE falla (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE falla OWNER TO postgres;

--
-- TOC entry 295 (class 1259 OID 97237)
-- Name: falla_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE falla_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE falla_id_seq OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 96192)
-- Name: fallapresupuesto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE fallapresupuesto (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idfalla bigint,
    idpresupuesto bigint
);


ALTER TABLE fallapresupuesto OWNER TO postgres;

--
-- TOC entry 296 (class 1259 OID 97239)
-- Name: fallapresupuesto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fallapresupuesto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fallapresupuesto_id_seq OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 96197)
-- Name: funcion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE funcion (
    id bigint NOT NULL,
    clave character varying(255) NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    icono character varying(255),
    nombre character varying(255) NOT NULL,
    url character varying(255),
    idfuncionpadre bigint,
    idsistema bigint NOT NULL
);


ALTER TABLE funcion OWNER TO postgres;

--
-- TOC entry 297 (class 1259 OID 97241)
-- Name: funcion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE funcion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE funcion_id_seq OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 96207)
-- Name: garaje; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE garaje (
    garaje_id bigint NOT NULL,
    descripcion character varying(255),
    usuario_id bigint
);


ALTER TABLE garaje OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 96212)
-- Name: garantia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE garantia (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estado character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    tiempovigencia integer NOT NULL,
    idtipogarantia bigint NOT NULL
);


ALTER TABLE garantia OWNER TO postgres;

--
-- TOC entry 298 (class 1259 OID 97243)
-- Name: garantia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE garantia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE garantia_id_seq OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 96222)
-- Name: grosoraceite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE grosoraceite (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE grosoraceite OWNER TO postgres;

--
-- TOC entry 299 (class 1259 OID 97245)
-- Name: grosoraceite_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grosoraceite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE grosoraceite_id_seq OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 96232)
-- Name: grupo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE grupo (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    nombre character varying(255) NOT NULL
);


ALTER TABLE grupo OWNER TO postgres;

--
-- TOC entry 300 (class 1259 OID 97247)
-- Name: grupo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grupo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE grupo_id_seq OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 96242)
-- Name: habilidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE habilidad (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE habilidad OWNER TO postgres;

--
-- TOC entry 301 (class 1259 OID 97249)
-- Name: habilidad_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE habilidad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE habilidad_id_seq OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 96252)
-- Name: herramienta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE herramienta (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE herramienta OWNER TO postgres;

--
-- TOC entry 302 (class 1259 OID 97251)
-- Name: herramienta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE herramienta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE herramienta_id_seq OWNER TO postgres;

--
-- TOC entry 303 (class 1259 OID 97253)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 96260)
-- Name: horario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE horario (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE horario OWNER TO postgres;

--
-- TOC entry 304 (class 1259 OID 97255)
-- Name: horario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE horario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE horario_id_seq OWNER TO postgres;

--
-- TOC entry 383 (class 1259 OID 97555)
-- Name: jet_bookmark; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE jet_bookmark (
    id integer NOT NULL,
    url character varying(200) NOT NULL,
    title character varying(255) NOT NULL,
    "user" integer NOT NULL,
    date_add timestamp with time zone NOT NULL,
    CONSTRAINT jet_bookmark_user_check CHECK (("user" >= 0))
);


ALTER TABLE jet_bookmark OWNER TO postgres;

--
-- TOC entry 382 (class 1259 OID 97553)
-- Name: jet_bookmark_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE jet_bookmark_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE jet_bookmark_id_seq OWNER TO postgres;

--
-- TOC entry 3536 (class 0 OID 0)
-- Dependencies: 382
-- Name: jet_bookmark_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE jet_bookmark_id_seq OWNED BY jet_bookmark.id;


--
-- TOC entry 385 (class 1259 OID 97564)
-- Name: jet_pinnedapplication; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE jet_pinnedapplication (
    id integer NOT NULL,
    app_label character varying(255) NOT NULL,
    "user" integer NOT NULL,
    date_add timestamp with time zone NOT NULL,
    CONSTRAINT jet_pinnedapplication_user_check CHECK (("user" >= 0))
);


ALTER TABLE jet_pinnedapplication OWNER TO postgres;

--
-- TOC entry 384 (class 1259 OID 97562)
-- Name: jet_pinnedapplication_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE jet_pinnedapplication_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE jet_pinnedapplication_id_seq OWNER TO postgres;

--
-- TOC entry 3537 (class 0 OID 0)
-- Dependencies: 384
-- Name: jet_pinnedapplication_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE jet_pinnedapplication_id_seq OWNED BY jet_pinnedapplication.id;


--
-- TOC entry 214 (class 1259 OID 96270)
-- Name: job; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE job (
    id bigint NOT NULL,
    clave integer NOT NULL,
    estado character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    nombre character varying(255) NOT NULL
);


ALTER TABLE job OWNER TO postgres;

--
-- TOC entry 305 (class 1259 OID 97257)
-- Name: job_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE job_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE job_id_seq OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 96280)
-- Name: log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE log (
    id integer NOT NULL,
    date timestamp without time zone,
    message character varying(255) NOT NULL
);


ALTER TABLE log OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 96285)
-- Name: marca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE marca (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    nombre character varying(255) NOT NULL
);


ALTER TABLE marca OWNER TO postgres;

--
-- TOC entry 308 (class 1259 OID 97263)
-- Name: marca_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE marca_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE marca_id_seq OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 96293)
-- Name: marcarepuesto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE marcarepuesto (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE marcarepuesto OWNER TO postgres;

--
-- TOC entry 306 (class 1259 OID 97259)
-- Name: marcarepuesto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE marcarepuesto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE marcarepuesto_id_seq OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 96303)
-- Name: marcatecnologia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE marcatecnologia (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    tiempoestimadoejecucion timestamp without time zone,
    tiempomaximoejecucion timestamp without time zone
);


ALTER TABLE marcatecnologia OWNER TO postgres;

--
-- TOC entry 307 (class 1259 OID 97261)
-- Name: marcatecnologia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE marcatecnologia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE marcatecnologia_id_seq OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 96311)
-- Name: modelo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE modelo (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    nombre character varying(255) NOT NULL,
    idmarca bigint
);


ALTER TABLE modelo OWNER TO postgres;

--
-- TOC entry 309 (class 1259 OID 97265)
-- Name: modelo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE modelo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE modelo_id_seq OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 96321)
-- Name: motivo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE motivo (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE motivo OWNER TO postgres;

--
-- TOC entry 310 (class 1259 OID 97267)
-- Name: motivo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE motivo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE motivo_id_seq OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 96329)
-- Name: motor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE motor (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    serial character varying(255) NOT NULL,
    idtipomotor bigint NOT NULL
);


ALTER TABLE motor OWNER TO postgres;

--
-- TOC entry 311 (class 1259 OID 97269)
-- Name: motor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE motor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE motor_id_seq OWNER TO postgres;

--
-- TOC entry 386 (class 1259 OID 97594)
-- Name: noticia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE noticia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE noticia_id_seq OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 96337)
-- Name: noticia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE noticia (
    id integer DEFAULT nextval('noticia_id_seq'::regclass) NOT NULL,
    descripcion character varying(1000) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    titulo character varying(255) NOT NULL,
    urlimagen character varying(255)
);


ALTER TABLE noticia OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 96347)
-- Name: notificacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE notificacion (
    id bigint NOT NULL,
    descripcion character varying(255),
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    nombre character varying(255) NOT NULL,
    ideventualidad bigint,
    idordenentrega bigint,
    idpresupuesto bigint,
    idpromocion bigint,
    idtiponotificacion bigint
);


ALTER TABLE notificacion OWNER TO postgres;

--
-- TOC entry 312 (class 1259 OID 97273)
-- Name: notificacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE notificacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE notificacion_id_seq OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 96355)
-- Name: notificacionusuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE notificacionusuario (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idnotificacion bigint,
    idusuario bigint
);


ALTER TABLE notificacionusuario OWNER TO postgres;

--
-- TOC entry 313 (class 1259 OID 97275)
-- Name: notificacionusuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE notificacionusuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE notificacionusuario_id_seq OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 96360)
-- Name: ocupacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ocupacion (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE ocupacion OWNER TO postgres;

--
-- TOC entry 314 (class 1259 OID 97277)
-- Name: ocupacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ocupacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ocupacion_id_seq OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 96370)
-- Name: ordenentrega; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ordenentrega (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estado character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idgarantia bigint NOT NULL,
    idprueba bigint NOT NULL
);


ALTER TABLE ordenentrega OWNER TO postgres;

--
-- TOC entry 315 (class 1259 OID 97279)
-- Name: ordenentrega_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ordenentrega_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ordenentrega_id_seq OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 96380)
-- Name: ordenservicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ordenservicio (
    id bigint NOT NULL,
    descripcion character varying(255),
    estado character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idpresupuesto bigint NOT NULL
);


ALTER TABLE ordenservicio OWNER TO postgres;

--
-- TOC entry 316 (class 1259 OID 97281)
-- Name: ordenservicio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ordenservicio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ordenservicio_id_seq OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 96388)
-- Name: ordenserviciousuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ordenserviciousuario (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idordenservicio bigint,
    idusuario bigint
);


ALTER TABLE ordenserviciousuario OWNER TO postgres;

--
-- TOC entry 317 (class 1259 OID 97283)
-- Name: ordenserviciousuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ordenserviciousuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ordenserviciousuario_id_seq OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 96393)
-- Name: pasatiempo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pasatiempo (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE pasatiempo OWNER TO postgres;

--
-- TOC entry 318 (class 1259 OID 97285)
-- Name: pasatiempo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pasatiempo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pasatiempo_id_seq OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 96403)
-- Name: permiso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE permiso (
    id bigint NOT NULL,
    acceso boolean NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreaccion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idaccion bigint NOT NULL,
    idfuncion bigint NOT NULL,
    idgrupo bigint NOT NULL
);


ALTER TABLE permiso OWNER TO postgres;

--
-- TOC entry 319 (class 1259 OID 97287)
-- Name: permiso_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE permiso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE permiso_id_seq OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 96408)
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE persona (
    id bigint NOT NULL,
    apellido character varying(255) NOT NULL,
    cedula character varying(255) NOT NULL,
    direccion character varying(255),
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    fechanacimiento timestamp without time zone,
    nombre character varying(255) NOT NULL,
    sexo character varying(255),
    telefono character varying(255),
    urlfoto character varying(255),
    iddeporte bigint,
    idocupacion bigint,
    idpasatiempo bigint,
    idprofesion bigint
);


ALTER TABLE persona OWNER TO postgres;

--
-- TOC entry 320 (class 1259 OID 97289)
-- Name: persona_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE persona_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persona_id_seq OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 96418)
-- Name: preguntafrecuente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE preguntafrecuente (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    pregunta character varying(1000) NOT NULL,
    respuesta character varying(1000) NOT NULL
);


ALTER TABLE preguntafrecuente OWNER TO postgres;

--
-- TOC entry 321 (class 1259 OID 97291)
-- Name: preguntafrecuente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE preguntafrecuente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE preguntafrecuente_id_seq OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 96426)
-- Name: presupuesto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE presupuesto (
    id bigint NOT NULL,
    descripcion character varying(255),
    estado character varying(255),
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    monto_total real,
    idcita bigint
);


ALTER TABLE presupuesto OWNER TO postgres;

--
-- TOC entry 322 (class 1259 OID 97293)
-- Name: presupuesto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE presupuesto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE presupuesto_id_seq OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 96434)
-- Name: presupuestoservicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE presupuestoservicio (
    id bigint NOT NULL,
    estatus character varying(255),
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idpresupuesto bigint,
    idservicio bigint
);


ALTER TABLE presupuestoservicio OWNER TO postgres;

--
-- TOC entry 323 (class 1259 OID 97295)
-- Name: presupuestoservicio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE presupuestoservicio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE presupuestoservicio_id_seq OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 96439)
-- Name: presupuestotiporepuesto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE presupuestotiporepuesto (
    id bigint NOT NULL,
    estatus character varying(255),
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idpresupuesto bigint,
    idtiporepuesto bigint
);


ALTER TABLE presupuestotiporepuesto OWNER TO postgres;

--
-- TOC entry 324 (class 1259 OID 97297)
-- Name: presupuestotiporepuesto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE presupuestotiporepuesto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE presupuestotiporepuesto_id_seq OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 96444)
-- Name: profesion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE profesion (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE profesion OWNER TO postgres;

--
-- TOC entry 325 (class 1259 OID 97299)
-- Name: profesion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE profesion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE profesion_id_seq OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 96454)
-- Name: promocion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE promocion (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    descuento double precision NOT NULL,
    estado character varying(255),
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    fechavigenciafin timestamp without time zone,
    fechavigenciainicio timestamp without time zone,
    imagen character varying(255),
    titulo character varying(255) NOT NULL,
    idservicio bigint NOT NULL
);


ALTER TABLE promocion OWNER TO postgres;

--
-- TOC entry 326 (class 1259 OID 97301)
-- Name: promocion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE promocion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE promocion_id_seq OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 96464)
-- Name: prueba; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prueba (
    id bigint NOT NULL,
    descripcion character varying(255),
    estado character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idordenservicio bigint
);


ALTER TABLE prueba OWNER TO postgres;

--
-- TOC entry 327 (class 1259 OID 97303)
-- Name: prueba_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE prueba_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE prueba_id_seq OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 96472)
-- Name: reclamo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE reclamo (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estado character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idmotivo bigint,
    idordenentrega bigint,
    idtiporeclamo bigint NOT NULL
);


ALTER TABLE reclamo OWNER TO postgres;

--
-- TOC entry 328 (class 1259 OID 97305)
-- Name: reclamo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE reclamo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE reclamo_id_seq OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 96482)
-- Name: rectificacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE rectificacion (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE rectificacion OWNER TO postgres;

--
-- TOC entry 329 (class 1259 OID 97307)
-- Name: rectificacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE rectificacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rectificacion_id_seq OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 96492)
-- Name: redsocial; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE redsocial (
    id bigint NOT NULL,
    estatus character varying(255),
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    nombre character varying(255),
    url character varying(255),
    idsistema bigint
);


ALTER TABLE redsocial OWNER TO postgres;

--
-- TOC entry 330 (class 1259 OID 97309)
-- Name: redsocial_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE redsocial_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE redsocial_id_seq OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 96502)
-- Name: refrigerante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE refrigerante (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idtiporefrigerante bigint NOT NULL
);


ALTER TABLE refrigerante OWNER TO postgres;

--
-- TOC entry 331 (class 1259 OID 97311)
-- Name: refrigerante_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE refrigerante_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE refrigerante_id_seq OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 96510)
-- Name: servicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE servicio (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estado character varying(255),
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    tarifa double precision NOT NULL,
    titulo character varying(255),
    urlimagen character varying(255)
);


ALTER TABLE servicio OWNER TO postgres;

--
-- TOC entry 332 (class 1259 OID 97313)
-- Name: servicio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE servicio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE servicio_id_seq OWNER TO postgres;

--
-- TOC entry 333 (class 1259 OID 97315)
-- Name: servicioetapa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE servicioetapa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE servicioetapa_id_seq OWNER TO postgres;

--
-- TOC entry 334 (class 1259 OID 97317)
-- Name: servicioherramienta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE servicioherramienta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE servicioherramienta_id_seq OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 96518)
-- Name: serviciosetapas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE serviciosetapas (
    id bigint NOT NULL,
    estatus character varying(255),
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idetapa bigint,
    idservicio bigint
);


ALTER TABLE serviciosetapas OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 96523)
-- Name: serviciosherramientas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE serviciosherramientas (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idherramienta bigint,
    idservicio bigint
);


ALTER TABLE serviciosherramientas OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 96528)
-- Name: serviciostecnologias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE serviciostecnologias (
    id bigint NOT NULL,
    estatus character varying(255),
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idservicio bigint,
    idtecnologia bigint
);


ALTER TABLE serviciostecnologias OWNER TO postgres;

--
-- TOC entry 335 (class 1259 OID 97319)
-- Name: serviciotecnologia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE serviciotecnologia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE serviciotecnologia_id_seq OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 96533)
-- Name: sesion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sesion (
    seseion_id bigint NOT NULL,
    descripcion character varying(255),
    ultima_sesion timestamp without time zone,
    usuario_id bigint
);


ALTER TABLE sesion OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 96538)
-- Name: sistema; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sistema (
    id bigint NOT NULL,
    celular character varying(255),
    correo character varying(255),
    descripcion character varying(255) NOT NULL,
    direccion character varying(5000),
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    filosofia character varying(5000),
    icono character varying(255),
    mapa character varying(5000),
    mision character varying(5000),
    nombre character varying(255) NOT NULL,
    telefono character varying(255),
    timelinetwitter character varying(5000),
    timelinetwittervisible boolean,
    urlimagencabezera character varying(255),
    urlimagencabezeravisible boolean,
    vision character varying(5000)
);


ALTER TABLE sistema OWNER TO postgres;

--
-- TOC entry 336 (class 1259 OID 97321)
-- Name: sistema_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sistema_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sistema_id_seq OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 96548)
-- Name: slider; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE slider (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    nombre character varying(255) NOT NULL,
    urlimagen character varying(255)
);


ALTER TABLE slider OWNER TO postgres;

--
-- TOC entry 337 (class 1259 OID 97323)
-- Name: slider_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE slider_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE slider_id_seq OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 96558)
-- Name: tarifa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tarifa (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    monto double precision
);


ALTER TABLE tarifa OWNER TO postgres;

--
-- TOC entry 338 (class 1259 OID 97325)
-- Name: tarifa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tarifa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tarifa_id_seq OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 96566)
-- Name: tecnologia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tecnologia (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tecnologia OWNER TO postgres;

--
-- TOC entry 339 (class 1259 OID 97327)
-- Name: tecnologia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tecnologia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tecnologia_id_seq OWNER TO postgres;

--
-- TOC entry 388 (class 1259 OID 97607)
-- Name: thumbnail_kvstore; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE thumbnail_kvstore (
    key character varying(200) NOT NULL,
    value text NOT NULL
);


ALTER TABLE thumbnail_kvstore OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 96574)
-- Name: tipoaceite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipoaceite (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tipoaceite OWNER TO postgres;

--
-- TOC entry 340 (class 1259 OID 97329)
-- Name: tipoaceite_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipoaceite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipoaceite_id_seq OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 96584)
-- Name: tipocaja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipocaja (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tipocaja OWNER TO postgres;

--
-- TOC entry 341 (class 1259 OID 97331)
-- Name: tipocaja_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipocaja_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipocaja_id_seq OWNER TO postgres;

--
-- TOC entry 254 (class 1259 OID 96594)
-- Name: tipoclase; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipoclase (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tipoclase OWNER TO postgres;

--
-- TOC entry 342 (class 1259 OID 97333)
-- Name: tipoclase_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipoclase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipoclase_id_seq OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 96604)
-- Name: tipocombustible; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipocombustible (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tipocombustible OWNER TO postgres;

--
-- TOC entry 343 (class 1259 OID 97335)
-- Name: tipocombustible_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipocombustible_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipocombustible_id_seq OWNER TO postgres;

--
-- TOC entry 256 (class 1259 OID 96614)
-- Name: tipoeventualidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipoeventualidad (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tipoeventualidad OWNER TO postgres;

--
-- TOC entry 344 (class 1259 OID 97337)
-- Name: tipoeventualidad_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipoeventualidad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipoeventualidad_id_seq OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 96624)
-- Name: tipogarantia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipogarantia (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    nombre character varying(255) NOT NULL
);


ALTER TABLE tipogarantia OWNER TO postgres;

--
-- TOC entry 345 (class 1259 OID 97339)
-- Name: tipogarantia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipogarantia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipogarantia_id_seq OWNER TO postgres;

--
-- TOC entry 258 (class 1259 OID 96634)
-- Name: tipomotor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipomotor (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tipomotor OWNER TO postgres;

--
-- TOC entry 346 (class 1259 OID 97341)
-- Name: tipomotor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipomotor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipomotor_id_seq OWNER TO postgres;

--
-- TOC entry 259 (class 1259 OID 96644)
-- Name: tiponotificacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tiponotificacion (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    icono character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL
);


ALTER TABLE tiponotificacion OWNER TO postgres;

--
-- TOC entry 352 (class 1259 OID 97353)
-- Name: tiponotificacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tiponotificacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tiponotificacion_id_seq OWNER TO postgres;

--
-- TOC entry 260 (class 1259 OID 96652)
-- Name: tiporeclamo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tiporeclamo (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tiporeclamo OWNER TO postgres;

--
-- TOC entry 347 (class 1259 OID 97343)
-- Name: tiporeclamo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tiporeclamo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tiporeclamo_id_seq OWNER TO postgres;

--
-- TOC entry 261 (class 1259 OID 96662)
-- Name: tiporefrigerante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tiporefrigerante (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tiporefrigerante OWNER TO postgres;

--
-- TOC entry 348 (class 1259 OID 97345)
-- Name: tiporefrigerante_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tiporefrigerante_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tiporefrigerante_id_seq OWNER TO postgres;

--
-- TOC entry 262 (class 1259 OID 96672)
-- Name: tiporepuesto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tiporepuesto (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tiporepuesto OWNER TO postgres;

--
-- TOC entry 349 (class 1259 OID 97347)
-- Name: tiporepuesto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tiporepuesto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tiporepuesto_id_seq OWNER TO postgres;

--
-- TOC entry 263 (class 1259 OID 96680)
-- Name: tiposervicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tiposervicio (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tiposervicio OWNER TO postgres;

--
-- TOC entry 350 (class 1259 OID 97349)
-- Name: tiposervicio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tiposervicio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tiposervicio_id_seq OWNER TO postgres;

--
-- TOC entry 264 (class 1259 OID 96688)
-- Name: tipotecnologia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipotecnologia (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE tipotecnologia OWNER TO postgres;

--
-- TOC entry 351 (class 1259 OID 97351)
-- Name: tipotecnologia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipotecnologia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipotecnologia_id_seq OWNER TO postgres;

--
-- TOC entry 265 (class 1259 OID 96696)
-- Name: uso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE uso (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE uso OWNER TO postgres;

--
-- TOC entry 353 (class 1259 OID 97355)
-- Name: uso_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE uso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE uso_id_seq OWNER TO postgres;

--
-- TOC entry 266 (class 1259 OID 96706)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE usuario (
    id bigint NOT NULL,
    contrasenna character varying(255) NOT NULL,
    correo character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    pregunta character varying(255) NOT NULL,
    respuesta character varying(255) NOT NULL,
    idgrupo bigint,
    idpersona bigint NOT NULL
);


ALTER TABLE usuario OWNER TO postgres;

--
-- TOC entry 355 (class 1259 OID 97359)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_id_seq OWNER TO postgres;

--
-- TOC entry 267 (class 1259 OID 96716)
-- Name: usuariohabilidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE usuariohabilidad (
    id bigint NOT NULL,
    habilidad_id bigint,
    usuario_id bigint
);


ALTER TABLE usuariohabilidad OWNER TO postgres;

--
-- TOC entry 354 (class 1259 OID 97357)
-- Name: usuariohabilidad_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuariohabilidad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuariohabilidad_id_seq OWNER TO postgres;

--
-- TOC entry 268 (class 1259 OID 96721)
-- Name: vehiculo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE vehiculo (
    id bigint NOT NULL,
    anno integer,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    kilometraje real,
    nropuestos integer,
    placa character varying(255) NOT NULL,
    serialcarroceria character varying(255),
    serialmotor character varying(255),
    idcaja bigint,
    idclase bigint,
    idcolor bigint,
    idcombustible bigint,
    idgrosoraceite bigint,
    idmarca bigint,
    idmodelo bigint,
    idrefrigerante bigint,
    idtipoaceite bigint,
    iduso bigint,
    idusuario bigint NOT NULL
);


ALTER TABLE vehiculo OWNER TO postgres;

--
-- TOC entry 357 (class 1259 OID 97363)
-- Name: vehiculo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE vehiculo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vehiculo_id_seq OWNER TO postgres;

--
-- TOC entry 269 (class 1259 OID 96731)
-- Name: vehiculoaccesorio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE vehiculoaccesorio (
    id bigint NOT NULL,
    estatus character varying(255),
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone,
    idaccesorio bigint,
    idvehiculo bigint
);


ALTER TABLE vehiculoaccesorio OWNER TO postgres;

--
-- TOC entry 356 (class 1259 OID 97361)
-- Name: vehiculoaccesorio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE vehiculoaccesorio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vehiculoaccesorio_id_seq OWNER TO postgres;

--
-- TOC entry 270 (class 1259 OID 96736)
-- Name: vehiculoservicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE vehiculoservicio (
    id bigint NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone NOT NULL,
    fechamodificacion timestamp without time zone,
    idservicio bigint,
    idvehiculo bigint
);


ALTER TABLE vehiculoservicio OWNER TO postgres;

--
-- TOC entry 358 (class 1259 OID 97365)
-- Name: vehiculoservicio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE vehiculoservicio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vehiculoservicio_id_seq OWNER TO postgres;

--
-- TOC entry 271 (class 1259 OID 96741)
-- Name: viaje; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE viaje (
    id bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    estatus character varying(255) NOT NULL,
    fechacreacion timestamp without time zone,
    fechamodificacion timestamp without time zone
);


ALTER TABLE viaje OWNER TO postgres;

--
-- TOC entry 359 (class 1259 OID 97367)
-- Name: viaje_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE viaje_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE viaje_id_seq OWNER TO postgres;

--
-- TOC entry 2762 (class 2604 OID 97404)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_group ALTER COLUMN id SET DEFAULT nextval('auth_group_id_seq'::regclass);


--
-- TOC entry 2763 (class 2604 OID 97414)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_group_permissions ALTER COLUMN id SET DEFAULT nextval('auth_group_permissions_id_seq'::regclass);


--
-- TOC entry 2761 (class 2604 OID 97396)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_permission ALTER COLUMN id SET DEFAULT nextval('auth_permission_id_seq'::regclass);


--
-- TOC entry 2764 (class 2604 OID 97422)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user ALTER COLUMN id SET DEFAULT nextval('auth_user_id_seq'::regclass);


--
-- TOC entry 2765 (class 2604 OID 97432)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user_groups ALTER COLUMN id SET DEFAULT nextval('auth_user_groups_id_seq'::regclass);


--
-- TOC entry 2766 (class 2604 OID 97440)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user_user_permissions ALTER COLUMN id SET DEFAULT nextval('auth_user_user_permissions_id_seq'::regclass);


--
-- TOC entry 2772 (class 2604 OID 97544)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY constance_config ALTER COLUMN id SET DEFAULT nextval('constance_config_id_seq'::regclass);


--
-- TOC entry 2769 (class 2604 OID 97531)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dashboard_userdashboardmodule ALTER COLUMN id SET DEFAULT nextval('dashboard_userdashboardmodule_id_seq'::regclass);


--
-- TOC entry 2767 (class 2604 OID 97500)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY django_admin_log ALTER COLUMN id SET DEFAULT nextval('django_admin_log_id_seq'::regclass);


--
-- TOC entry 2760 (class 2604 OID 97386)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY django_content_type ALTER COLUMN id SET DEFAULT nextval('django_content_type_id_seq'::regclass);


--
-- TOC entry 2759 (class 2604 OID 97375)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY django_migrations ALTER COLUMN id SET DEFAULT nextval('django_migrations_id_seq'::regclass);


--
-- TOC entry 2773 (class 2604 OID 97558)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jet_bookmark ALTER COLUMN id SET DEFAULT nextval('jet_bookmark_id_seq'::regclass);


--
-- TOC entry 2775 (class 2604 OID 97567)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jet_pinnedapplication ALTER COLUMN id SET DEFAULT nextval('jet_pinnedapplication_id_seq'::regclass);


--
-- TOC entry 3309 (class 0 OID 96008)
-- Dependencies: 181
-- Data for Name: accesorio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY accesorio (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Luces Delantera	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Luces Traseras	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Luces Internas	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Atena del Capot	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Atena en el Techo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Tapon de gasolina	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Carrocerias sin golpes	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	Calefaccion	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	Cinturones	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
10	Radio	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3538 (class 0 OID 0)
-- Dependencies: 272
-- Name: accesorio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('accesorio_id_seq', 10, true);


--
-- TOC entry 3310 (class 0 OID 96016)
-- Dependencies: 182
-- Data for Name: accion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY accion (id, estatus, fechacreacion, fechamodificacion, nombre) FROM stdin;
1	Activo	2017-03-22 08:50:06.591	\N	INCLUIR
2	Activo	2017-03-22 08:50:06.591	\N	CONSULTAR
3	Activo	2017-03-22 08:50:06.591	\N	EDITAR
4	Activo	2017-03-22 08:50:06.591	\N	ELIMINAR
5	Activo	2017-03-22 08:50:06.591	\N	DESPLEGAR
6	Activo	2017-03-22 08:50:06.591	\N	LISTAR
\.


--
-- TOC entry 3539 (class 0 OID 0)
-- Dependencies: 273
-- Name: accion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('accion_id_seq', 6, true);


--
-- TOC entry 3311 (class 0 OID 96026)
-- Dependencies: 183
-- Data for Name: aceite; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY aceite (id, descripcion, estatus, fechacreacion, fechamodificacion, idgrosoraceite, idtipoaceite) FROM stdin;
\.


--
-- TOC entry 3540 (class 0 OID 0)
-- Dependencies: 274
-- Name: aceite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('aceite_id_seq', 1, false);


--
-- TOC entry 3312 (class 0 OID 96034)
-- Dependencies: 184
-- Data for Name: agenda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY agenda (id, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3541 (class 0 OID 0)
-- Dependencies: 275
-- Name: agenda_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('agenda_id_seq', 1, true);


--
-- TOC entry 3313 (class 0 OID 96039)
-- Dependencies: 185
-- Data for Name: anomalia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY anomalia (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Fallas Adiccionales Detectadas para Presupuestar	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Fallas Adiccionales Presupestadas	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Costos Adiccionales Presupestados	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Costos No Presupestados	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Servicios Adicionales Sugeridos del Cliente	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3542 (class 0 OID 0)
-- Dependencies: 276
-- Name: anomalia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('anomalia_id_seq', 5, true);


--
-- TOC entry 3314 (class 0 OID 96049)
-- Dependencies: 186
-- Data for Name: asunto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY asunto (id, estatus, fechacreacion, fechamodificacion, nombre) FROM stdin;
\.


--
-- TOC entry 3543 (class 0 OID 0)
-- Dependencies: 277
-- Name: asunto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('asunto_id_seq', 1, false);


--
-- TOC entry 3495 (class 0 OID 97401)
-- Dependencies: 367
-- Data for Name: auth_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auth_group (id, name) FROM stdin;
\.


--
-- TOC entry 3544 (class 0 OID 0)
-- Dependencies: 366
-- Name: auth_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('auth_group_id_seq', 1, false);


--
-- TOC entry 3497 (class 0 OID 97411)
-- Dependencies: 369
-- Data for Name: auth_group_permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auth_group_permissions (id, group_id, permission_id) FROM stdin;
\.


--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 368
-- Name: auth_group_permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('auth_group_permissions_id_seq', 1, false);


--
-- TOC entry 3493 (class 0 OID 97393)
-- Dependencies: 365
-- Data for Name: auth_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auth_permission (id, name, content_type_id, codename) FROM stdin;
1	Can add user dashboard module	1	add_userdashboardmodule
2	Can change user dashboard module	1	change_userdashboardmodule
3	Can delete user dashboard module	1	delete_userdashboardmodule
4	Can change config	2	change_config
5	Can add bookmark	3	add_bookmark
6	Can change bookmark	3	change_bookmark
7	Can delete bookmark	3	delete_bookmark
8	Can add pinned application	4	add_pinnedapplication
9	Can change pinned application	4	change_pinnedapplication
10	Can delete pinned application	4	delete_pinnedapplication
11	Can add log entry	5	add_logentry
12	Can change log entry	5	change_logentry
13	Can delete log entry	5	delete_logentry
14	Can add permission	6	add_permission
15	Can change permission	6	change_permission
16	Can delete permission	6	delete_permission
17	Can add user	7	add_user
18	Can change user	7	change_user
19	Can delete user	7	delete_user
20	Can add group	8	add_group
21	Can change group	8	change_group
22	Can delete group	8	delete_group
23	Can add content type	9	add_contenttype
24	Can change content type	9	change_contenttype
25	Can delete content type	9	delete_contenttype
26	Can add session	10	add_session
27	Can change session	10	change_session
28	Can delete session	10	delete_session
29	Can add kv store	11	add_kvstore
30	Can change kv store	11	change_kvstore
31	Can delete kv store	11	delete_kvstore
32	Can add constance	12	add_constance
33	Can change constance	12	change_constance
34	Can delete constance	12	delete_constance
35	Can add accesorio	13	add_accesorio
36	Can change accesorio	13	change_accesorio
37	Can delete accesorio	13	delete_accesorio
38	Can add accion	14	add_accion
39	Can change accion	14	change_accion
40	Can delete accion	14	delete_accion
41	Can add aceite	15	add_aceite
42	Can change aceite	15	change_aceite
43	Can delete aceite	15	delete_aceite
44	Can add agenda	16	add_agenda
45	Can change agenda	16	change_agenda
46	Can delete agenda	16	delete_agenda
47	Can add anomalia	17	add_anomalia
48	Can change anomalia	17	change_anomalia
49	Can delete anomalia	17	delete_anomalia
50	Can add asunto	18	add_asunto
51	Can change asunto	18	change_asunto
52	Can delete asunto	18	delete_asunto
53	Can add caja	19	add_caja
54	Can change caja	19	change_caja
55	Can delete caja	19	delete_caja
56	Can add cita	20	add_cita
57	Can change cita	20	change_cita
58	Can delete cita	20	delete_cita
59	Can add ciudad	21	add_ciudad
60	Can change ciudad	21	change_ciudad
61	Can delete ciudad	21	delete_ciudad
62	Can add clase	22	add_clase
63	Can change clase	22	change_clase
64	Can delete clase	22	delete_clase
65	Can add color	23	add_color
66	Can change color	23	change_color
67	Can delete color	23	delete_color
68	Can add combustible	24	add_combustible
69	Can change combustible	24	change_combustible
70	Can delete combustible	24	delete_combustible
71	Can add deporte	25	add_deporte
72	Can change deporte	25	change_deporte
73	Can delete deporte	25	delete_deporte
74	Can add espacio	26	add_espacio
75	Can change espacio	26	change_espacio
76	Can delete espacio	26	delete_espacio
77	Can add espacioordenservicio	27	add_espacioordenservicio
78	Can change espacioordenservicio	27	change_espacioordenservicio
79	Can delete espacioordenservicio	27	delete_espacioordenservicio
80	Can add estado	28	add_estado
81	Can change estado	28	change_estado
82	Can delete estado	28	delete_estado
83	Can add etapa	29	add_etapa
84	Can change etapa	29	change_etapa
85	Can delete etapa	29	delete_etapa
86	Can add eventualidad	30	add_eventualidad
87	Can change eventualidad	30	change_eventualidad
88	Can delete eventualidad	30	delete_eventualidad
89	Can add falla	31	add_falla
90	Can change falla	31	change_falla
91	Can delete falla	31	delete_falla
92	Can add fallapresupuesto	32	add_fallapresupuesto
93	Can change fallapresupuesto	32	change_fallapresupuesto
94	Can delete fallapresupuesto	32	delete_fallapresupuesto
95	Can add funcion	33	add_funcion
96	Can change funcion	33	change_funcion
97	Can delete funcion	33	delete_funcion
98	Can add garaje	34	add_garaje
99	Can change garaje	34	change_garaje
100	Can delete garaje	34	delete_garaje
101	Can add garantia	35	add_garantia
102	Can change garantia	35	change_garantia
103	Can delete garantia	35	delete_garantia
104	Can add grosoraceite	36	add_grosoraceite
105	Can change grosoraceite	36	change_grosoraceite
106	Can delete grosoraceite	36	delete_grosoraceite
107	Can add grupo	37	add_grupo
108	Can change grupo	37	change_grupo
109	Can delete grupo	37	delete_grupo
110	Can add habilidad	38	add_habilidad
111	Can change habilidad	38	change_habilidad
112	Can delete habilidad	38	delete_habilidad
113	Can add herramienta	39	add_herramienta
114	Can change herramienta	39	change_herramienta
115	Can delete herramienta	39	delete_herramienta
116	Can add horario	40	add_horario
117	Can change horario	40	change_horario
118	Can delete horario	40	delete_horario
119	Can add job	41	add_job
120	Can change job	41	change_job
121	Can delete job	41	delete_job
122	Can add log	42	add_log
123	Can change log	42	change_log
124	Can delete log	42	delete_log
125	Can add marca	43	add_marca
126	Can change marca	43	change_marca
127	Can delete marca	43	delete_marca
128	Can add marcarepuesto	44	add_marcarepuesto
129	Can change marcarepuesto	44	change_marcarepuesto
130	Can delete marcarepuesto	44	delete_marcarepuesto
131	Can add marcatecnologia	45	add_marcatecnologia
132	Can change marcatecnologia	45	change_marcatecnologia
133	Can delete marcatecnologia	45	delete_marcatecnologia
134	Can add modelo	46	add_modelo
135	Can change modelo	46	change_modelo
136	Can delete modelo	46	delete_modelo
137	Can add motivo	47	add_motivo
138	Can change motivo	47	change_motivo
139	Can delete motivo	47	delete_motivo
140	Can add motor	48	add_motor
141	Can change motor	48	change_motor
142	Can delete motor	48	delete_motor
143	Can add notificacion	49	add_notificacion
144	Can change notificacion	49	change_notificacion
145	Can delete notificacion	49	delete_notificacion
146	Can add notificacionusuario	50	add_notificacionusuario
147	Can change notificacionusuario	50	change_notificacionusuario
148	Can delete notificacionusuario	50	delete_notificacionusuario
149	Can add ocupacion	51	add_ocupacion
150	Can change ocupacion	51	change_ocupacion
151	Can delete ocupacion	51	delete_ocupacion
152	Can add ordenentrega	52	add_ordenentrega
153	Can change ordenentrega	52	change_ordenentrega
154	Can delete ordenentrega	52	delete_ordenentrega
155	Can add ordenservicio	53	add_ordenservicio
156	Can change ordenservicio	53	change_ordenservicio
157	Can delete ordenservicio	53	delete_ordenservicio
158	Can add ordenserviciousuario	54	add_ordenserviciousuario
159	Can change ordenserviciousuario	54	change_ordenserviciousuario
160	Can delete ordenserviciousuario	54	delete_ordenserviciousuario
161	Can add pasatiempo	55	add_pasatiempo
162	Can change pasatiempo	55	change_pasatiempo
163	Can delete pasatiempo	55	delete_pasatiempo
164	Can add permiso	56	add_permiso
165	Can change permiso	56	change_permiso
166	Can delete permiso	56	delete_permiso
167	Can add persona	57	add_persona
168	Can change persona	57	change_persona
169	Can delete persona	57	delete_persona
170	Can add preguntafrecuente	58	add_preguntafrecuente
171	Can change preguntafrecuente	58	change_preguntafrecuente
172	Can delete preguntafrecuente	58	delete_preguntafrecuente
173	Can add presupuesto	59	add_presupuesto
174	Can change presupuesto	59	change_presupuesto
175	Can delete presupuesto	59	delete_presupuesto
176	Can add profesion	60	add_profesion
177	Can change profesion	60	change_profesion
178	Can delete profesion	60	delete_profesion
179	Can add promocion	61	add_promocion
180	Can change promocion	61	change_promocion
181	Can delete promocion	61	delete_promocion
182	Can add prueba	62	add_prueba
183	Can change prueba	62	change_prueba
184	Can delete prueba	62	delete_prueba
185	Can add reclamo	63	add_reclamo
186	Can change reclamo	63	change_reclamo
187	Can delete reclamo	63	delete_reclamo
188	Can add rectificacion	64	add_rectificacion
189	Can change rectificacion	64	change_rectificacion
190	Can delete rectificacion	64	delete_rectificacion
191	Can add redsocial	65	add_redsocial
192	Can change redsocial	65	change_redsocial
193	Can delete redsocial	65	delete_redsocial
194	Can add refrigerante	66	add_refrigerante
195	Can change refrigerante	66	change_refrigerante
196	Can delete refrigerante	66	delete_refrigerante
197	Can add servicio	67	add_servicio
198	Can change servicio	67	change_servicio
199	Can delete servicio	67	delete_servicio
200	Can add serviciopresupuesto	68	add_serviciopresupuesto
201	Can change serviciopresupuesto	68	change_serviciopresupuesto
202	Can delete serviciopresupuesto	68	delete_serviciopresupuesto
203	Can add serviciosetapas	69	add_serviciosetapas
204	Can change serviciosetapas	69	change_serviciosetapas
205	Can delete serviciosetapas	69	delete_serviciosetapas
206	Can add serviciosherramientas	70	add_serviciosherramientas
207	Can change serviciosherramientas	70	change_serviciosherramientas
208	Can delete serviciosherramientas	70	delete_serviciosherramientas
209	Can add serviciostecnologias	71	add_serviciostecnologias
210	Can change serviciostecnologias	71	change_serviciostecnologias
211	Can delete serviciostecnologias	71	delete_serviciostecnologias
212	Can add sesion	72	add_sesion
213	Can change sesion	72	change_sesion
214	Can delete sesion	72	delete_sesion
215	Can add sistema	73	add_sistema
216	Can change sistema	73	change_sistema
217	Can delete sistema	73	delete_sistema
218	Can add slider	74	add_slider
219	Can change slider	74	change_slider
220	Can delete slider	74	delete_slider
221	Can add tarifa	75	add_tarifa
222	Can change tarifa	75	change_tarifa
223	Can delete tarifa	75	delete_tarifa
224	Can add tecnologia	76	add_tecnologia
225	Can change tecnologia	76	change_tecnologia
226	Can delete tecnologia	76	delete_tecnologia
227	Can add tipoaceite	77	add_tipoaceite
228	Can change tipoaceite	77	change_tipoaceite
229	Can delete tipoaceite	77	delete_tipoaceite
230	Can add tipocaja	78	add_tipocaja
231	Can change tipocaja	78	change_tipocaja
232	Can delete tipocaja	78	delete_tipocaja
233	Can add tipoclase	79	add_tipoclase
234	Can change tipoclase	79	change_tipoclase
235	Can delete tipoclase	79	delete_tipoclase
236	Can add tipocombustible	80	add_tipocombustible
237	Can change tipocombustible	80	change_tipocombustible
238	Can delete tipocombustible	80	delete_tipocombustible
239	Can add tipoeventualidad	81	add_tipoeventualidad
240	Can change tipoeventualidad	81	change_tipoeventualidad
241	Can delete tipoeventualidad	81	delete_tipoeventualidad
242	Can add tipogarantia	82	add_tipogarantia
243	Can change tipogarantia	82	change_tipogarantia
244	Can delete tipogarantia	82	delete_tipogarantia
245	Can add tipomotor	83	add_tipomotor
246	Can change tipomotor	83	change_tipomotor
247	Can delete tipomotor	83	delete_tipomotor
248	Can add tiponotificacion	84	add_tiponotificacion
249	Can change tiponotificacion	84	change_tiponotificacion
250	Can delete tiponotificacion	84	delete_tiponotificacion
251	Can add tiporeclamo	85	add_tiporeclamo
252	Can change tiporeclamo	85	change_tiporeclamo
253	Can delete tiporeclamo	85	delete_tiporeclamo
254	Can add tiporefrigerante	86	add_tiporefrigerante
255	Can change tiporefrigerante	86	change_tiporefrigerante
256	Can delete tiporefrigerante	86	delete_tiporefrigerante
257	Can add tiporepuesto	87	add_tiporepuesto
258	Can change tiporepuesto	87	change_tiporepuesto
259	Can delete tiporepuesto	87	delete_tiporepuesto
260	Can add tiposervicio	88	add_tiposervicio
261	Can change tiposervicio	88	change_tiposervicio
262	Can delete tiposervicio	88	delete_tiposervicio
263	Can add tipotecnologia	89	add_tipotecnologia
264	Can change tipotecnologia	89	change_tipotecnologia
265	Can delete tipotecnologia	89	delete_tipotecnologia
266	Can add uso	90	add_uso
267	Can change uso	90	change_uso
268	Can delete uso	90	delete_uso
269	Can add usuario	91	add_usuario
270	Can change usuario	91	change_usuario
271	Can delete usuario	91	delete_usuario
272	Can add usuariohabilidad	92	add_usuariohabilidad
273	Can change usuariohabilidad	92	change_usuariohabilidad
274	Can delete usuariohabilidad	92	delete_usuariohabilidad
275	Can add vehiculo	93	add_vehiculo
276	Can change vehiculo	93	change_vehiculo
277	Can delete vehiculo	93	delete_vehiculo
278	Can add vehiculoaccesorio	94	add_vehiculoaccesorio
279	Can change vehiculoaccesorio	94	change_vehiculoaccesorio
280	Can delete vehiculoaccesorio	94	delete_vehiculoaccesorio
281	Can add viaje	95	add_viaje
282	Can change viaje	95	change_viaje
283	Can delete viaje	95	delete_viaje
284	Can add noticia	96	add_noticia
285	Can change noticia	96	change_noticia
286	Can delete noticia	96	delete_noticia
\.


--
-- TOC entry 3546 (class 0 OID 0)
-- Dependencies: 364
-- Name: auth_permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('auth_permission_id_seq', 286, true);


--
-- TOC entry 3499 (class 0 OID 97419)
-- Dependencies: 371
-- Data for Name: auth_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auth_user (id, password, last_login, is_superuser, username, first_name, last_name, email, is_staff, is_active, date_joined) FROM stdin;
\.


--
-- TOC entry 3501 (class 0 OID 97429)
-- Dependencies: 373
-- Data for Name: auth_user_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auth_user_groups (id, user_id, group_id) FROM stdin;
\.


--
-- TOC entry 3547 (class 0 OID 0)
-- Dependencies: 372
-- Name: auth_user_groups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('auth_user_groups_id_seq', 1, false);


--
-- TOC entry 3548 (class 0 OID 0)
-- Dependencies: 370
-- Name: auth_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('auth_user_id_seq', 1, false);


--
-- TOC entry 3503 (class 0 OID 97437)
-- Dependencies: 375
-- Data for Name: auth_user_user_permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auth_user_user_permissions (id, user_id, permission_id) FROM stdin;
\.


--
-- TOC entry 3549 (class 0 OID 0)
-- Dependencies: 374
-- Name: auth_user_user_permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('auth_user_user_permissions_id_seq', 1, false);


--
-- TOC entry 3315 (class 0 OID 96059)
-- Dependencies: 187
-- Data for Name: caja; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY caja (id, descripcion, estatus, fechacreacion, fechamodificacion, idtipocaja) FROM stdin;
1	Automaticas	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
2	Manuales	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	2
3	Secuenciales	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	3
\.


--
-- TOC entry 3550 (class 0 OID 0)
-- Dependencies: 278
-- Name: caja_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('caja_id_seq', 3, true);


--
-- TOC entry 3316 (class 0 OID 96067)
-- Dependencies: 188
-- Data for Name: calificacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY calificacion (id, calificacionatencion, calificacioninstalacion, calificacionservicio, comentario, estatus, fecha, idordenentrega) FROM stdin;
\.


--
-- TOC entry 3551 (class 0 OID 0)
-- Dependencies: 279
-- Name: calificacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('calificacion_id_seq', 1, false);


--
-- TOC entry 3317 (class 0 OID 96075)
-- Dependencies: 189
-- Data for Name: cita; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cita (id, descripcion, estado, estatus, fecha, fechacreacion, fechamodificacion, nombrereferido, idespacio, ideventualidad, idmotivo, idpromocion, idservicio, idvehiculo) FROM stdin;
1701142656	Falla en Balanceo.	Pendiente	Activo	2017-03-22 14:29:29.632143	2017-03-22 14:29:29.632161	\N	Juan Perez	\N	\N	\N	\N	4	1
1403436417	Cambio de Correa. Se tiene una Falla.	Pendiente	Activo	2017-03-22 14:31:35.918657	2017-03-22 14:31:35.918675	\N	Liscano	\N	\N	\N	\N	2	8
\.


--
-- TOC entry 3552 (class 0 OID 0)
-- Dependencies: 280
-- Name: cita_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cita_id_seq', 1, false);


--
-- TOC entry 3318 (class 0 OID 96083)
-- Dependencies: 190
-- Data for Name: ciudad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ciudad (id, descripcion, estatus, fechacreacion, fechamodificacion, idestado) FROM stdin;
\.


--
-- TOC entry 3553 (class 0 OID 0)
-- Dependencies: 281
-- Name: ciudad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ciudad_id_seq', 1, false);


--
-- TOC entry 3319 (class 0 OID 96091)
-- Dependencies: 191
-- Data for Name: clase; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY clase (id, descripcion, estatus, fechacreacion, fechamodificacion, idtipoclase) FROM stdin;
1	Automovil	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
2	Automovil	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	3
3	Automovil	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	3
4	Automovil	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	4
5	Automovil	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	5
6	Automovil	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	6
7	Automovil	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	7
8	Automovil	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	8
9	Microbus	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
10	Microbus	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	9
11	Panel	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
12	Panel	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	9
13	Pick Up	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
14	Pick Up	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	11
15	Pick Up	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	12
16	Pick Up	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	13
17	Pick Up	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	14
18	Pick Up	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	14
\.


--
-- TOC entry 3554 (class 0 OID 0)
-- Dependencies: 282
-- Name: clase_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('clase_id_seq', 18, true);


--
-- TOC entry 3320 (class 0 OID 96099)
-- Dependencies: 192
-- Data for Name: color; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY color (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Rojo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Negro	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Blanco	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Azul	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Naranja	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Gris	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Vinotinto	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	Amarillo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	Verde	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
10	Dorado	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3555 (class 0 OID 0)
-- Dependencies: 283
-- Name: color_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('color_id_seq', 10, true);


--
-- TOC entry 3321 (class 0 OID 96109)
-- Dependencies: 193
-- Data for Name: combustible; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY combustible (id, descripcion, estatus, fechacreacion, fechamodificacion, idtipocombustible) FROM stdin;
1	Gasolina sin plomo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
2	Gasolina con plomo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	2
3	Diesel	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	3
4	Diesel	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	4
\.


--
-- TOC entry 3556 (class 0 OID 0)
-- Dependencies: 284
-- Name: combustible_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('combustible_id_seq', 4, true);


--
-- TOC entry 3322 (class 0 OID 96117)
-- Dependencies: 194
-- Data for Name: configuracionservicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY configuracionservicio (id, visible, idservicio) FROM stdin;
\.


--
-- TOC entry 3557 (class 0 OID 0)
-- Dependencies: 285
-- Name: configuracionservicio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('configuracionservicio_id_seq', 1, false);


--
-- TOC entry 3509 (class 0 OID 97541)
-- Dependencies: 381
-- Data for Name: constance_config; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY constance_config (id, key, value) FROM stdin;
\.


--
-- TOC entry 3558 (class 0 OID 0)
-- Dependencies: 380
-- Name: constance_config_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('constance_config_id_seq', 1, false);


--
-- TOC entry 3507 (class 0 OID 97528)
-- Dependencies: 379
-- Data for Name: dashboard_userdashboardmodule; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY dashboard_userdashboardmodule (id, title, module, app_label, "user", "column", "order", settings, children, collapsed) FROM stdin;
\.


--
-- TOC entry 3559 (class 0 OID 0)
-- Dependencies: 378
-- Name: dashboard_userdashboardmodule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('dashboard_userdashboardmodule_id_seq', 1, false);


--
-- TOC entry 3323 (class 0 OID 96122)
-- Dependencies: 195
-- Data for Name: deporte; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY deporte (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Futbol	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Tenis	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Metra	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Voleibol	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Basket	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Piques Fangueros	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Automovilismo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	Ciclismo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	Futbol Sala	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3560 (class 0 OID 0)
-- Dependencies: 286
-- Name: deporte_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('deporte_id_seq', 9, true);


--
-- TOC entry 3505 (class 0 OID 97497)
-- Dependencies: 377
-- Data for Name: django_admin_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY django_admin_log (id, action_time, object_id, object_repr, action_flag, change_message, content_type_id, user_id) FROM stdin;
\.


--
-- TOC entry 3561 (class 0 OID 0)
-- Dependencies: 376
-- Name: django_admin_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('django_admin_log_id_seq', 1, false);


--
-- TOC entry 3491 (class 0 OID 97383)
-- Dependencies: 363
-- Data for Name: django_content_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY django_content_type (id, app_label, model) FROM stdin;
1	dashboard	userdashboardmodule
2	constance	config
3	jet	bookmark
4	jet	pinnedapplication
5	admin	logentry
6	auth	permission
7	auth	user
8	auth	group
9	contenttypes	contenttype
10	sessions	session
11	thumbnail	kvstore
12	database	constance
13	main	accesorio
14	main	accion
15	main	aceite
16	main	agenda
17	main	anomalia
18	main	asunto
19	main	caja
20	main	cita
21	main	ciudad
22	main	clase
23	main	color
24	main	combustible
25	main	deporte
26	main	espacio
27	main	espacioordenservicio
28	main	estado
29	main	etapa
30	main	eventualidad
31	main	falla
32	main	fallapresupuesto
33	main	funcion
34	main	garaje
35	main	garantia
36	main	grosoraceite
37	main	grupo
38	main	habilidad
39	main	herramienta
40	main	horario
41	main	job
42	main	log
43	main	marca
44	main	marcarepuesto
45	main	marcatecnologia
46	main	modelo
47	main	motivo
48	main	motor
49	main	notificacion
50	main	notificacionusuario
51	main	ocupacion
52	main	ordenentrega
53	main	ordenservicio
54	main	ordenserviciousuario
55	main	pasatiempo
56	main	permiso
57	main	persona
58	main	preguntafrecuente
59	main	presupuesto
60	main	profesion
61	main	promocion
62	main	prueba
63	main	reclamo
64	main	rectificacion
65	main	redsocial
66	main	refrigerante
67	main	servicio
68	main	serviciopresupuesto
69	main	serviciosetapas
70	main	serviciosherramientas
71	main	serviciostecnologias
72	main	sesion
73	main	sistema
74	main	slider
75	main	tarifa
76	main	tecnologia
77	main	tipoaceite
78	main	tipocaja
79	main	tipoclase
80	main	tipocombustible
81	main	tipoeventualidad
82	main	tipogarantia
83	main	tipomotor
84	main	tiponotificacion
85	main	tiporeclamo
86	main	tiporefrigerante
87	main	tiporepuesto
88	main	tiposervicio
89	main	tipotecnologia
90	main	uso
91	main	usuario
92	main	usuariohabilidad
93	main	vehiculo
94	main	vehiculoaccesorio
95	main	viaje
96	main	noticia
\.


--
-- TOC entry 3562 (class 0 OID 0)
-- Dependencies: 362
-- Name: django_content_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('django_content_type_id_seq', 96, true);


--
-- TOC entry 3489 (class 0 OID 97372)
-- Dependencies: 361
-- Data for Name: django_migrations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY django_migrations (id, app, name, applied) FROM stdin;
1	contenttypes	0001_initial	2017-03-22 10:12:53.524417-04
2	auth	0001_initial	2017-03-22 10:12:54.538986-04
3	admin	0001_initial	2017-03-22 10:12:54.848744-04
4	admin	0002_logentry_remove_auto_add	2017-03-22 10:12:54.89279-04
5	contenttypes	0002_remove_content_type_name	2017-03-22 10:12:54.959457-04
6	auth	0002_alter_permission_name_max_length	2017-03-22 10:12:54.992767-04
7	auth	0003_alter_user_email_max_length	2017-03-22 10:12:55.026075-04
8	auth	0004_alter_user_username_opts	2017-03-22 10:12:55.056218-04
9	auth	0005_alter_user_last_login_null	2017-03-22 10:12:55.092711-04
10	auth	0006_require_contenttypes_0002	2017-03-22 10:12:55.104476-04
11	auth	0007_alter_validators_add_error_messages	2017-03-22 10:12:55.12977-04
12	auth	0008_alter_user_username_max_length	2017-03-22 10:12:55.203757-04
13	dashboard	0001_initial	2017-03-22 10:12:55.315363-04
14	database	0001_initial	2017-03-22 10:12:55.527028-04
15	jet	0001_initial	2017-03-22 10:12:55.838211-04
16	jet	0002_delete_userdashboardmodule	2017-03-22 10:12:55.861181-04
17	main	0001_initial	2017-03-22 10:12:56.449876-04
18	main	0002_auto_20170318_1855	2017-03-22 10:12:56.484457-04
19	main	0003_auto_20170318_1856	2017-03-22 10:12:56.729084-04
20	sessions	0001_initial	2017-03-22 10:12:56.915917-04
21	thumbnail	0001_initial	2017-03-22 10:12:57.082771-04
\.


--
-- TOC entry 3563 (class 0 OID 0)
-- Dependencies: 360
-- Name: django_migrations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('django_migrations_id_seq', 21, true);


--
-- TOC entry 3515 (class 0 OID 97597)
-- Dependencies: 387
-- Data for Name: django_session; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY django_session (session_key, session_data, expire_date) FROM stdin;
m009krgsea62wsgskveozjf9u4pewhxd	ZWI4OTJjNWZiNjZkYjU1NzRhZDliZWJhY2RhY2Q3ZDM3NDc0MjBlZTp7ImN0eCI6eyJjZWR1bGEiOiI1MjQxOTAyIiwiZW5jb250cmFkbyI6Im5vIn19	2017-04-05 10:29:59.812363-04
\.


--
-- TOC entry 3324 (class 0 OID 96132)
-- Dependencies: 196
-- Data for Name: espacio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY espacio (id, descripcion, estado, estatus, fechacreacion, fechamodificacion, identificacion, idagenda) FROM stdin;
1	Espacio1	f	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	Espacio#1	1
2	Espacio2	f	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	Espacio#2	1
3	Espacio3	f	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	Espacio#3	1
4	Espacio4	f	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	Espacio#4	1
5	Espacio5	f	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	Espacio#5	1
6	Espacio6	f	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	Espacio#6	1
\.


--
-- TOC entry 3564 (class 0 OID 0)
-- Dependencies: 288
-- Name: espacio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('espacio_id_seq', 6, true);


--
-- TOC entry 3325 (class 0 OID 96140)
-- Dependencies: 197
-- Data for Name: espacioherramienta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY espacioherramienta (id, estatus, fechacreacion, fechamodificacion, idespacio, idherramienta) FROM stdin;
\.


--
-- TOC entry 3565 (class 0 OID 0)
-- Dependencies: 289
-- Name: espacioherramienta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('espacioherramienta_id_seq', 1, false);


--
-- TOC entry 3326 (class 0 OID 96145)
-- Dependencies: 198
-- Data for Name: espaciomecanico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY espaciomecanico (id, estatus, fechacreacion, fechamodificacion, idespacio, idmecanico) FROM stdin;
\.


--
-- TOC entry 3566 (class 0 OID 0)
-- Dependencies: 290
-- Name: espaciomecanico_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('espaciomecanico_id_seq', 1, false);


--
-- TOC entry 3327 (class 0 OID 96150)
-- Dependencies: 199
-- Data for Name: espacioordenservicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY espacioordenservicio (id, estatus, fechacreacion, fechamodificacion, idespacio) FROM stdin;
\.


--
-- TOC entry 3567 (class 0 OID 0)
-- Dependencies: 287
-- Name: espacioordenservicio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('espacioordenservicio_id_seq', 1, false);


--
-- TOC entry 3328 (class 0 OID 96155)
-- Dependencies: 200
-- Data for Name: espaciotecnologia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY espaciotecnologia (id, estatus, fechacreacion, fechamodificacion, idespacio, idtecnologia) FROM stdin;
\.


--
-- TOC entry 3568 (class 0 OID 0)
-- Dependencies: 291
-- Name: espaciotecnologia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('espaciotecnologia_id_seq', 1, false);


--
-- TOC entry 3329 (class 0 OID 96160)
-- Dependencies: 201
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY estado (id, descripcion, estatus, fechacreacion, fechamodificacion, tiempoestimadoejecucion, tiempomaximoejecucion) FROM stdin;
\.


--
-- TOC entry 3569 (class 0 OID 0)
-- Dependencies: 292
-- Name: estado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('estado_id_seq', 1, false);


--
-- TOC entry 3330 (class 0 OID 96168)
-- Dependencies: 202
-- Data for Name: etapa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY etapa (id, descripcion, estatus, fechacreacion, fechamodificacion, tiempoestimadoejecucion, tiempomaximoejecucion) FROM stdin;
1	En desarme	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	\N	\N
2	En sustitucion de pieza	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	\N	\N
3	En armado	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	\N	\N
4	Finalizado	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	\N	\N
\.


--
-- TOC entry 3570 (class 0 OID 0)
-- Dependencies: 293
-- Name: etapa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('etapa_id_seq', 4, true);


--
-- TOC entry 3331 (class 0 OID 96176)
-- Dependencies: 203
-- Data for Name: eventualidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY eventualidad (id, descripcion, estatus, fechacreacion, fechamodificacion, idtipoeventualidad) FROM stdin;
1	El Cliente No Se Puede Presentar a La Cita el Dia Citado	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	1
2	El Cliente No Se Presento el Dia de la Cita	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	1
3	El Cliente Ya No Rerquiere el Servicio	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	1
4	El Taller No Laborara el Dia Pautado	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	2
5	No Se Encuentra Disponible el Servicio Solicitado en este Momento	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	2
6	El Taller Con Cuenta con Espacio Disponible En Pocos Dias	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	2
7	Tecnologias o Herramientas No Disponible Para Prestar el Servicio	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	2
8	No se Encuentra Disponible el Personal Para Prestar el Servicio	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	2
\.


--
-- TOC entry 3571 (class 0 OID 0)
-- Dependencies: 294
-- Name: eventualidad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('eventualidad_id_seq', 8, true);


--
-- TOC entry 3332 (class 0 OID 96184)
-- Dependencies: 204
-- Data for Name: falla; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY falla (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	calentamiento	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Motor de Arranque	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Frenos	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Fisibles	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Crijido en el Cambio de la Velocidad 	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Perdida del Poder en el Motor 	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Bote de Agua 	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	No funciona el encendido	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	Falla en la bomba e gasolina	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
10	Falla en la correa del tiempo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3572 (class 0 OID 0)
-- Dependencies: 295
-- Name: falla_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('falla_id_seq', 10, true);


--
-- TOC entry 3333 (class 0 OID 96192)
-- Dependencies: 205
-- Data for Name: fallapresupuesto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fallapresupuesto (id, estatus, fechacreacion, fechamodificacion, idfalla, idpresupuesto) FROM stdin;
\.


--
-- TOC entry 3573 (class 0 OID 0)
-- Dependencies: 296
-- Name: fallapresupuesto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fallapresupuesto_id_seq', 1, false);


--
-- TOC entry 3334 (class 0 OID 96197)
-- Dependencies: 206
-- Data for Name: funcion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY funcion (id, clave, descripcion, estatus, fechacreacion, fechamodificacion, icono, nombre, url, idfuncionpadre, idsistema) FROM stdin;
1	nivel1Inicio	Inicio	Activo	2017-03-22 08:50:06.824	\N		Inicio	prime.zul	\N	1
2	nivel1AdministracionRecursos		Activo	2017-03-22 08:50:06.871	\N		Administracion de Recursos	\N	\N	1
3	nivel2General		Activo	2017-03-22 08:50:06.895	\N		General	\N	2	1
4	nivel3Cliente		Activo	2017-03-22 08:50:06.924	\N		Opciones de Cliente	\N	3	1
5	nivel4Ocupacion		Activo	2017-03-22 08:50:06.947	\N		Ocupación	pc/ocupacion/frm-ocupacion-catalogo.zul	4	1
6	nivel4Profesion		Activo	2017-03-22 08:50:06.968	\N		Profesión	pc/profesion/frm-profesion-catalogo.zul	4	1
7	nivel4Pasatiempo		Activo	2017-03-22 08:50:06.979	\N		Pasatiempo	pc/pasatiempo/frm-pasatiempo-catalogo.zul	4	1
8	nivel4Ciudad		Activo	2017-03-22 08:50:06.99	\N		Ciudad	gc/ciudad/frm-ciudad-catalogo.zul	4	1
9	nivel4Estado		Activo	2017-03-22 08:50:07.001	\N		Estado	gc/estado/frm-estado-catalogo.zul	4	1
10	nivel4Cliente		Activo	2017-03-22 08:50:07.024	\N		Cliente	gs/recepcion/frm-recepcion-cliente.zul	4	1
11	nivel4Motivo		Activo	2017-03-22 08:50:07.035	\N		Motivo	gc/motivo/frm-motivo-catalogo.zul	4	1
12	nivel4Deporte		Activo	2017-03-22 08:50:07.046	\N		Deporte	pc/deporte/frm-deporte-catalogo.zul	4	1
13	nivel4Viaje		Activo	2017-03-22 08:50:07.057	\N		Viaje	pc/viaje/frm-viaje-catalogo.zul	4	1
14	nivel3Vehiculo		Activo	2017-03-22 08:50:07.079	\N		Opciones de Vehículo	\N	3	1
15	nivel4Marca		Activo	2017-03-22 08:50:07.09	\N		Marca	gc/marca/frm-marca-catalogo.zul	14	1
16	nivel4Modelo		Activo	2017-03-22 08:50:07.101	\N		Modelo	gc/modelo/frm-modelo-catalogo.zul	14	1
17	nivel4Uso		Activo	2017-03-22 08:50:07.123	\N		Uso	\N	14	1
18	nivel4Color		Activo	2017-03-22 08:50:07.134	\N		Color	gc/color/frm-color-catalogo.zul	14	1
19	nivel4TipoMotor		Activo	2017-03-22 08:50:07.157	\N		Tipo de Motor	gc/tipoMotor/frm-tipoMotor-catalogo.zul	14	1
20	nivel4Motor		Activo	2017-03-22 08:50:07.168	\N		Motor	gc/motor/frm-motor-catalogo.zul	14	1
21	nivel4Rectificacion		Activo	2017-03-22 08:50:07.179	\N		Rectificación	gc/rectificacion/frm-rectificacion-catalogo.zul	14	1
22	nivel4TipoClase		Activo	2017-03-22 08:50:07.19	\N		Tipo de Clase	gc/tipoClase/frm-tipoClase-catalogo.zul	14	1
23	nivel4Clase		Activo	2017-03-22 08:50:07.202	\N		Clase	gc/clase/frm-clase-catalogo.zul	14	1
24	nivel4MarcaRepuesto		Activo	2017-03-22 08:50:07.212	\N		Marca de Repuesto	gc/marcaRepuesto/frm-marcaRepuesto-catalogo.zul	14	1
25	nivel4TipoCaja		Activo	2017-03-22 08:50:07.234	\N		Tipo de Caja	gc/tipoCaja/frm-tipoCaja-catalogo.zul	14	1
26	nivel4Caja		Activo	2017-03-22 08:50:07.245	\N		Caja	gc/caja/frm-caja-catalogo.zul	14	1
27	nivel4TipoAceite		Activo	2017-03-22 08:50:07.268	\N		Tipo de Aceite	gc/tipoAceite/frm-tipoAceite-catalogo.zul	14	1
28	nivel4GrosorAceite		Activo	2017-03-22 08:50:07.279	\N		Grosor de Aceite	gc/grosorAceite/frm-grosorAceite-catalogo.zul	14	1
29	nivel4Aceite		Activo	2017-03-22 08:50:07.29	\N		Aceite	gc/aceite/frm-aceite-catalogo.zul	14	1
30	nivel4TipoCombustible		Activo	2017-03-22 08:50:07.301	\N		Tipo de Combustible	gc/tipoCombustible/frm-tipoCombustible-catalogo.zul	14	1
31	nivel4Conbustible		Activo	2017-03-22 08:50:07.313	\N		Combustible	gc/combustible/frm-combustible-catalogo.zul	14	1
32	nivel4TipoRefrigerante		Activo	2017-03-22 08:50:07.323	\N		Tipo Refrigerante	gc/tipoRefrigerante/frm-tipoRefrigerante-catalogo.zul	14	1
33	nivel4Refrigerante		Activo	2017-03-22 08:50:07.334	\N		Refrigerante	gc/refrigerante/frm-refrigerante-catalogo.zul	14	1
34	nivel4TipoTecnologia		Activo	2017-03-22 08:50:07.345	\N		Tipo de Tecnología	gc/tipoTecnologia/frm-tipoTecnologia-catalogo.zul	14	1
35	nivel4MarcaTecnologia		Activo	2017-03-22 08:50:07.356	\N		Marca de Tecnología	gc/marcaTecnologia/frm-marcaTecnologia-catalogo.zul	14	1
36	nivel4TipoRepuesto		Activo	2017-03-22 08:50:07.368	\N		Tipo de Repuesto	gc/tipoRepuesto/frm-tipoRepuesto-catalogo.zul	14	1
37	nivel4Vehiculo		Activo	2017-03-22 08:50:07.39	\N		Vehículo	\N	14	1
38	nivel3Personal		Activo	2017-03-22 08:50:07.401	\N		Opciones de Personal	\N	3	1
39	nivel4Personal		Activo	2017-03-22 08:50:07.412	\N		Personal	\N	38	1
40	nivel2PromocionOferta		Activo	2017-03-22 08:50:07.423	\N		Promoción y Oferta	\N	2	1
41	nivel3Tarifa		Activo	2017-03-22 08:50:07.434	\N		Tarifa	gc/tarifa/frm-tarifa-catalogo.zul	40	1
42	nivel3Descuento		Activo	2017-03-22 08:50:07.445	\N		Descuento	gc/descuento/frm-descuento-catalogo.zul	40	1
43	nivel3Promocion		Activo	2017-03-22 08:50:07.457	\N		Promoción	\N	40	1
44	nivel2AgendaCitas		Activo	2017-03-22 08:50:07.467	\N		Agenda y Citas	\N	2	1
45	nivel3Habilidad		Activo	2017-03-22 08:50:07.49	\N		Habilidades	gc/habilidad/frm-habilidad-catalogo.zul	44	1
46	nivel3Espacio		Activo	2017-03-22 08:50:07.501	\N		Espacios del Taller	\N	44	1
47	nivel3TipoEventualidad		Activo	2017-03-22 08:50:07.512	\N		Tipo de Eventualidad	pc/tipoEventualidad/frm-tipoEventualidad-catalogo.zul	44	1
48	nivel2Servicio		Activo	2017-03-22 08:50:07.523	\N		Servicios	\N	2	1
49	nivel3Herramienta		Activo	2017-03-22 08:50:07.534	\N		Herramienta	\N	48	1
50	nivel3Tecnologia		Activo	2017-03-22 08:50:07.545	\N		Tecnología	\N	48	1
51	nivel3TipoServicio		Activo	2017-03-22 08:50:07.556	\N		Tipo de Servicio	\N	48	1
52	nivel3Anomalia		Activo	2017-03-22 08:50:07.579	\N		Anomalia	\N	48	1
53	nivel3Falla		Activo	2017-03-22 08:50:07.589	\N		Falla	\N	48	1
54	nivel2PostServicios		Activo	2017-03-22 08:50:07.601	\N		Post-Servicios	\N	2	1
55	nivel3TipoGarantia		Activo	2017-03-22 08:50:07.612	\N		Tipo de Garantía	\N	54	1
56	nivel3TipoReclamo		Activo	2017-03-22 08:50:07.634	\N		Tipo de Reclamo	\N	54	1
57	nivel1PromocionOferta		Activo	2017-03-22 08:50:07.656	\N		Promoción y Ofertas	\N	\N	1
58	nivel2CatalogoServicios		Activo	2017-03-22 08:50:07.667	\N		Catálogo de Servicios	\N	57	1
59	nivel3ConfigurarCatalogoServicios		Activo	2017-03-22 08:50:07.678	\N		Configurar	\N	58	1
60	nivel3ActualizarCatalogoServicios		Activo	2017-03-22 08:50:07.69	\N		Actualizar	\N	58	1
61	nivel2Promocion		Activo	2017-03-22 08:50:07.701	\N		Promoción	\N	57	1
62	nivel3ConfigurarPromocion		Activo	2017-03-22 08:50:07.714	\N		Configurar	gp/promocion/frm-promocion-catalogo.zul	61	1
63	nivel3ActualizarPromocion		Activo	2017-03-22 08:50:07.734	\N		Actualizar	\N	61	1
64	nivel1Agenda		Activo	2017-03-22 08:50:07.745	\N		Agenda	\N	\N	1
65	nivel2Planificacion		Activo	2017-03-22 08:50:07.756	\N		Planificación	\N	64	1
66	nivel3AsignarEspacio		Activo	2017-03-22 08:50:07.767	\N		Asignar Espacio	gc/espacio/frm-asignar-espacio-catalogo.zul	65	1
67	nivel1Citas		Activo	2017-03-22 08:50:07.789	\N		Citas	\N	\N	1
68	nivel2AtenderCita		Activo	2017-03-22 08:50:07.834	\N		Atender Citas	gac/frm-atender-cita.zul	67	1
69	nivel2CancelarCita		Activo	2017-03-22 08:50:07.845	\N		Cancelar Citas	gac/frm-cancelar-cita.zul	67	1
70	nivel1Servicios		Activo	2017-03-22 08:50:07.856	\N		Servicios	\N	\N	1
71	nivel2RecibirVehiculo		Activo	2017-03-22 08:50:07.867	\N		Recibir Vehículo	gs/recepcion/frm-recepcion.zul	70	1
72	nivel2Diagnosticar		Activo	2017-03-22 08:50:07.878	\N		Diagnósticar	gs/servicio/frm-diagnostico-catalogo.zul	70	1
73	nivel2AprobarPresupuesto		Activo	2017-03-22 08:50:07.889	\N		Aprobar Presupuesto	gs/aprobarPresupuesto/frm-presupuesto-catalogo.zul	70	1
74	nivel2GenerarOrdenServicio		Activo	2017-03-22 08:50:07.9	\N		Generar Orden de Servicio	gs/frm-orden-servicio-todos.zul	70	1
75	nivel2RevisarServicio		Activo	2017-03-22 08:50:07.911	\N		Revisar Servicio	gs/revisarServicioEjecutado/frm-revisar-catalogo.zul	70	1
76	nivel2GenerarOrdenEntrega		Activo	2017-03-22 08:50:07.922	\N		Generar Orden de Entrega	gs/ordenEntrega/frm-orden-entrega-catalogo.zul	70	1
77	nivel2CalificarServicio		Activo	2017-03-22 08:50:07.934	\N		Calificar Servicio	gs/calificarServicio/frm-calificar-servicio-catalogo.zul	70	1
78	nivel1PostServicios		Activo	2017-03-22 08:50:07.945	\N		Post-Servicios	\N	\N	1
79	nivel2SolicitudReclamo		Activo	2017-03-22 08:50:07.956	\N		Solicitud Reclamo	gps/reclamo/frm-reclamo-ordenesEntrega.zul	78	1
80	nivel2VerificarGarantia		Activo	2017-03-22 08:50:07.967	\N		Verificar Garantía	gps/reclamo/frm-reclamo-reclamos.zul	78	1
81	nivel1Reportes		Activo	2017-03-22 08:50:07.978	\N		Reportes	\N	\N	1
82	nivel2ReportesEstadistico		Activo	2017-03-22 08:50:08.022	\N		Reportes Estadístico	\N	81	1
83	nivel1AdministracionSistema		Activo	2017-03-22 08:50:08.033	\N		Administraci�n del Sistema	\N	\N	1
84	nivel2SeguridadFuncional		Activo	2017-03-22 08:50:08.044	\N		Seguridad Funcional	\N	83	1
85	nivel3Grupo		Activo	2017-03-22 08:50:08.055	\N		Grupo	gc/grupo/frm-grupo-catalogo.zul	84	1
86	nivel3Funcion		Activo	2017-03-22 08:50:08.067	\N		Función	gc/funcion/frm-funcion-catalogo.zul	84	1
87	nivel3Accion		Activo	2017-03-22 08:50:08.078	\N		Acción	gc/accion/frm-accion-catalogo.zul	84	1
88	nivel3AsignacionGrupos		Activo	2017-03-22 08:50:08.089	\N		Asignación Grupos	gc/grupo/frm-grupo-asignacion.zul	84	1
89	nivel3Usuario		Activo	2017-03-22 08:50:08.1	\N		Usuario	gc/grupo/usuario/frm-grupo-usuario-catalogo.zul	84	1
90	nivel3PerfilUsuario		Activo	2017-03-22 08:50:08.111	\N		Perfil de Usuario	\N	84	1
91	nivel2Web		Activo	2017-03-22 08:50:08.122	\N		Configuración del portal WEB	gc/web/configuracion-web.zul	83	1
92	nivel3ActualizarContenidoWeb		Activo	2017-03-22 08:50:08.133	\N		Actualizar Contenido	\N	84	1
93	nivel2Movil		Activo	2017-03-22 08:50:08.145	\N		Configuración App Movil	\N	83	1
94	nivel3ActualizarContenidoMovil		Activo	2017-03-22 08:50:08.156	\N		Actualizar Contenido	\N	93	1
95	nivel2Difusion		Activo	2017-03-22 08:50:08.167	\N		Configuración de Difusión	\N	83	1
96	nivel3CorreoElectronico		Activo	2017-03-22 08:50:08.178	\N		Correo Electr�nico	\N	95	1
97	nivel3RedesSociales		Activo	2017-03-22 08:50:08.189	\N		Redes Sociales	\N	95	1
98	nivel2BaseDatos		Activo	2017-03-22 08:50:08.2	\N		Base de Datos	\N	83	1
99	nivel3Respaldo		Activo	2017-03-22 08:50:08.211	\N		Respaldo	\N	98	1
100	nivel3Recuperacion		Activo	2017-03-22 08:50:08.222	\N		Recuperaci�n	\N	98	1
101	nivel3Historico		Activo	2017-03-22 08:50:08.233	\N		Historico	\N	98	1
\.


--
-- TOC entry 3574 (class 0 OID 0)
-- Dependencies: 297
-- Name: funcion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('funcion_id_seq', 101, true);


--
-- TOC entry 3335 (class 0 OID 96207)
-- Dependencies: 207
-- Data for Name: garaje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY garaje (garaje_id, descripcion, usuario_id) FROM stdin;
\.


--
-- TOC entry 3336 (class 0 OID 96212)
-- Dependencies: 208
-- Data for Name: garantia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY garantia (id, descripcion, estado, estatus, fechacreacion, fechamodificacion, tiempovigencia, idtipogarantia) FROM stdin;
1	Garantia en servicio	Vigente	Activo	2017-03-02 16:00:00	2017-03-02 16:00:00	7	2
2	Garantia por servicio	Vigente	Activo	2017-03-02 16:00:00	2017-03-02 16:00:00	7	2
3	Garantia servicio	Vigente	Activo	2017-03-02 16:00:00	2017-03-02 16:00:00	7	2
4	Garantia	Vigente	Activo	2017-03-02 16:00:00	2017-03-02 16:00:00	7	2
5	Garantia en mano de obra	Vigente	Activo	2017-03-02 16:00:00	2017-03-02 16:00:00	7	2
\.


--
-- TOC entry 3575 (class 0 OID 0)
-- Dependencies: 298
-- Name: garantia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('garantia_id_seq', 5, true);


--
-- TOC entry 3337 (class 0 OID 96222)
-- Dependencies: 209
-- Data for Name: grosoraceite; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grosoraceite (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	5W30	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	5W40	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	5W50	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	10w40	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	15w40	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	20w50	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	25w60	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3576 (class 0 OID 0)
-- Dependencies: 299
-- Name: grosoraceite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grosoraceite_id_seq', 7, true);


--
-- TOC entry 3338 (class 0 OID 96232)
-- Dependencies: 210
-- Data for Name: grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grupo (id, descripcion, estatus, fechacreacion, fechamodificacion, nombre) FROM stdin;
1	ADMINISTRADOR	Activo	2017-03-22 08:50:06.315	\N	ADMINISTRADOR
2	CLIENTE	Activo	2017-03-22 08:50:06.315	\N	CLIENTE
3	MECANICO	Activo	2017-03-22 08:50:06.315	\N	MECANICO
4	JEFE MECANICO	Activo	2017-03-22 08:50:06.315	\N	JEFE MECANICO
5	SECRETARIA	Activo	2017-03-22 08:50:06.315	\N	SECRETARIA
6	DEFAULT	Activo	2017-03-22 08:50:06.315	\N	DEFAULT
\.


--
-- TOC entry 3577 (class 0 OID 0)
-- Dependencies: 300
-- Name: grupo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grupo_id_seq', 6, true);


--
-- TOC entry 3339 (class 0 OID 96242)
-- Dependencies: 211
-- Data for Name: habilidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY habilidad (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Conocimiento en Tecnologa Scanner Automotriz	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Alta Capacidad en desmonte y armado de motores	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Alta Capacidad en las redes elctricas	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Alta Capacidad en los sistemas de encendido y las bateras	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Alta Capacidad en los sistemas de inyeccion de combustible	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Alta Capacidad en la electronica	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Alta capacidad en mantenimiento de herremientas utilizadas en el taller	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3578 (class 0 OID 0)
-- Dependencies: 301
-- Name: habilidad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('habilidad_id_seq', 7, true);


--
-- TOC entry 3340 (class 0 OID 96252)
-- Dependencies: 212
-- Data for Name: herramienta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY herramienta (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Martillo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Tornillo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Tubo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Medidores	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Gato Hidrulico	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Medidores	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Cinceles Neumticos	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	Llaves de Impacto	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	Trinquetes Neumticos	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3579 (class 0 OID 0)
-- Dependencies: 302
-- Name: herramienta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('herramienta_id_seq', 9, true);


--
-- TOC entry 3580 (class 0 OID 0)
-- Dependencies: 303
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- TOC entry 3341 (class 0 OID 96260)
-- Dependencies: 213
-- Data for Name: horario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY horario (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
\.


--
-- TOC entry 3581 (class 0 OID 0)
-- Dependencies: 304
-- Name: horario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('horario_id_seq', 1, false);


--
-- TOC entry 3511 (class 0 OID 97555)
-- Dependencies: 383
-- Data for Name: jet_bookmark; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY jet_bookmark (id, url, title, "user", date_add) FROM stdin;
\.


--
-- TOC entry 3582 (class 0 OID 0)
-- Dependencies: 382
-- Name: jet_bookmark_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('jet_bookmark_id_seq', 1, false);


--
-- TOC entry 3513 (class 0 OID 97564)
-- Dependencies: 385
-- Data for Name: jet_pinnedapplication; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY jet_pinnedapplication (id, app_label, "user", date_add) FROM stdin;
\.


--
-- TOC entry 3583 (class 0 OID 0)
-- Dependencies: 384
-- Name: jet_pinnedapplication_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('jet_pinnedapplication_id_seq', 1, false);


--
-- TOC entry 3342 (class 0 OID 96270)
-- Dependencies: 214
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY job (id, clave, estado, estatus, fechacreacion, fechamodificacion, nombre) FROM stdin;
1	1	Ejecutado	Activo	2017-03-22 08:50:06.12	2017-03-22 08:50:06.537	crearGrupos
2	2	Ejecutado	Activo	2017-03-22 08:50:06.12	2017-03-22 08:50:06.803	crearAcciones
3	3	Ejecutado	Activo	2017-03-22 08:50:06.12	2017-03-22 08:50:08.245	crearFunciones
4	4	Ejecutado	Activo	2017-03-22 08:50:06.12	2017-03-22 08:50:08.511	crearUsuarios
5	5	Ejecutado	Activo	2017-03-22 08:50:06.12	2017-03-22 08:50:35.842	crearPermisos
\.


--
-- TOC entry 3584 (class 0 OID 0)
-- Dependencies: 305
-- Name: job_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('job_id_seq', 5, true);


--
-- TOC entry 3343 (class 0 OID 96280)
-- Dependencies: 215
-- Data for Name: log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY log (id, date, message) FROM stdin;
\.


--
-- TOC entry 3344 (class 0 OID 96285)
-- Dependencies: 216
-- Data for Name: marca; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY marca (id, estatus, fechacreacion, fechamodificacion, nombre) FROM stdin;
1	Activo	2017-03-20 16:47:25.517	\N	MARCA
2	Activo	2017-03-20 16:47:25.517	\N	AGRALE
3	Activo	2017-03-20 16:47:25.517	\N	ALFA ROMEO
4	Activo	2017-03-20 16:47:25.517	\N	AUDI
5	Activo	2017-03-20 16:47:25.517	\N	BMW
6	Activo	2017-03-20 16:47:25.517	\N	CHERY
7	Activo	2017-03-20 16:47:25.517	\N	CHEVROLET
8	Activo	2017-03-20 16:47:25.517	\N	CHRYSLER
9	Activo	2017-03-20 16:47:25.517	\N	CITROEN
10	Activo	2017-03-20 16:47:25.517	\N	DACIA
11	Activo	2017-03-20 16:47:25.517	\N	DAEWO
12	Activo	2017-03-20 16:47:25.517	\N	DAIHATSU
13	Activo	2017-03-20 16:47:25.517	\N	DODGE
14	Activo	2017-03-20 16:47:25.517	\N	FERRARI
15	Activo	2017-03-20 16:47:25.517	\N	FIAT
16	Activo	2017-03-20 16:47:25.517	\N	FORD
17	Activo	2017-03-20 16:47:25.517	\N	GALLOPER
18	Activo	2017-03-20 16:47:25.517	\N	HEIBAO
19	Activo	2017-03-20 16:47:25.517	\N	HONDA
20	Activo	2017-03-20 16:47:25.517	\N	HYUNDAI
21	Activo	2017-03-20 16:47:25.517	\N	ISUZU
22	Activo	2017-03-20 16:47:25.517	\N	JAGUAR
23	Activo	2017-03-20 16:47:25.517	\N	JEEP
24	Activo	2017-03-20 16:47:25.517	\N	KIA
25	Activo	2017-03-20 16:47:25.517	\N	LADA
26	Activo	2017-03-20 16:47:25.517	\N	LAND ROVER
27	Activo	2017-03-20 16:47:25.517	\N	LEXUS
28	Activo	2017-03-20 16:47:25.517	\N	MASERATI
29	Activo	2017-03-20 16:47:25.517	\N	MAZDA
30	Activo	2017-03-20 16:47:25.517	\N	MERCEDES BENZ
31	Activo	2017-03-20 16:47:25.517	\N	MG
32	Activo	2017-03-20 16:47:25.517	\N	MINI
33	Activo	2017-03-20 16:47:25.517	\N	MITSUBISHI
34	Activo	2017-03-20 16:47:25.517	\N	NISSAN
35	Activo	2017-03-20 16:47:25.517	\N	PEUGEOT
36	Activo	2017-03-20 16:47:25.517	\N	PORSCHE
37	Activo	2017-03-20 16:47:25.517	\N	RAM
38	Activo	2017-03-20 16:47:25.517	\N	RENAULT
39	Activo	2017-03-20 16:47:25.517	\N	ROVER
40	Activo	2017-03-20 16:47:25.517	\N	SAAB
41	Activo	2017-03-20 16:47:25.517	\N	SEAT
42	Activo	2017-03-20 16:47:25.517	\N	SMART
43	Activo	2017-03-20 16:47:25.517	\N	SSANGYONG
44	Activo	2017-03-20 16:47:25.517	\N	SUBARU
45	Activo	2017-03-20 16:47:25.517	\N	SUZUKI
46	Activo	2017-03-20 16:47:25.517	\N	TATA
47	Activo	2017-03-20 16:47:25.517	\N	TOYOTA
48	Activo	2017-03-20 16:47:25.517	\N	VOLKSWAGEN
49	Activo	2017-03-20 16:47:25.517	\N	VOLVO
\.


--
-- TOC entry 3585 (class 0 OID 0)
-- Dependencies: 308
-- Name: marca_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('marca_id_seq', 1, false);


--
-- TOC entry 3345 (class 0 OID 96293)
-- Dependencies: 217
-- Data for Name: marcarepuesto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY marcarepuesto (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
\.


--
-- TOC entry 3586 (class 0 OID 0)
-- Dependencies: 306
-- Name: marcarepuesto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('marcarepuesto_id_seq', 1, false);


--
-- TOC entry 3346 (class 0 OID 96303)
-- Dependencies: 218
-- Data for Name: marcatecnologia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY marcatecnologia (id, descripcion, estatus, fechacreacion, fechamodificacion, tiempoestimadoejecucion, tiempomaximoejecucion) FROM stdin;
1	Multimarca	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	\N	\N
2	Iveco	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	\N	\N
3	Tecno Test	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	\N	\N
4	Autel	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	\N	\N
5	Launch	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	\N	\N
\.


--
-- TOC entry 3587 (class 0 OID 0)
-- Dependencies: 307
-- Name: marcatecnologia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('marcatecnologia_id_seq', 5, true);


--
-- TOC entry 3347 (class 0 OID 96311)
-- Dependencies: 219
-- Data for Name: modelo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY modelo (id, estatus, fechacreacion, fechamodificacion, nombre, idmarca) FROM stdin;
1	Activo	\N	\N	MODELO	1
2	Activo	\N	\N	MARRUA	2
3	Activo	\N	\N	147	3
4	Activo	\N	\N	156	3
5	Activo	\N	\N	159	3
6	Activo	\N	\N	166	3
7	Activo	\N	\N	BRERA	3
8	Activo	\N	\N	GIULIETTA	3
9	Activo	\N	\N	GT	3
10	Activo	\N	\N	GTV	3
11	Activo	\N	\N	MITO	3
12	Activo	\N	\N	SPIDER	3
13	Activo	\N	\N	A1	4
14	Activo	\N	\N	A3	4
15	Activo	\N	\N	A4	4
16	Activo	\N	\N	A5	4
17	Activo	\N	\N	A6	4
18	Activo	\N	\N	A7	4
19	Activo	\N	\N	A8	4
20	Activo	\N	\N	ALLROAD	4
21	Activo	\N	\N	Q3	4
22	Activo	\N	\N	Q5	4
23	Activo	\N	\N	Q7	4
24	Activo	\N	\N	R8	4
25	Activo	\N	\N	TT	4
26	Activo	\N	\N	SERIE1	5
27	Activo	\N	\N	SERIE3	5
28	Activo	\N	\N	SERIE5	5
29	Activo	\N	\N	SERIE6	5
30	Activo	\N	\N	SERIE7	5
31	Activo	\N	\N	X1	5
32	Activo	\N	\N	X3	5
33	Activo	\N	\N	X5	5
34	Activo	\N	\N	X6	5
35	Activo	\N	\N	Z3	5
36	Activo	\N	\N	Z4	5
37	Activo	\N	\N	FACE	6
38	Activo	\N	\N	FULWIN	6
39	Activo	\N	\N	QQ	6
40	Activo	\N	\N	SKIN	6
41	Activo	\N	\N	TIGGO	6
42	Activo	\N	\N	AGILE	7
43	Activo	\N	\N	ASTRA	7
44	Activo	\N	\N	ASTRA II	7
45	Activo	\N	\N	AVALANCHE	7
46	Activo	\N	\N	AVEO	7
47	Activo	\N	\N	BLAZER	7
48	Activo	\N	\N	CAMARO	7
49	Activo	\N	\N	CAPTIVA	7
50	Activo	\N	\N	CELTA	7
51	Activo	\N	\N	CLASSIC	7
52	Activo	\N	\N	COBALT	7
53	Activo	\N	\N	CORSA	7
54	Activo	\N	\N	CORSA CLASSIC	7
55	Activo	\N	\N	CORSA II	7
56	Activo	\N	\N	CORVETTE	7
57	Activo	\N	\N	CRUZE	7
58	Activo	\N	\N	MERIVA	7
59	Activo	\N	\N	MONTANA	7
60	Activo	\N	\N	ONIX	7
61	Activo	\N	\N	PRISMA	7
62	Activo	\N	\N	VECTRA	7
63	Activo	\N	\N	S-10	7
64	Activo	\N	\N	SILVERADO	7
65	Activo	\N	\N	SONIC	7
66	Activo	\N	\N	SPARK	7
67	Activo	\N	\N	SPIN	7
68	Activo	\N	\N	TRACKER	7
69	Activo	\N	\N	TRAILBLAZER	7
70	Activo	\N	\N	ZAFIRA	7
71	Activo	\N	\N	300	8
72	Activo	\N	\N	CARAVAN	8
73	Activo	\N	\N	TOWN & COUNTRY	8
74	Activo	\N	\N	GRAND CARAVAN	8
75	Activo	\N	\N	CROSSFIRE	8
76	Activo	\N	\N	NEON	8
77	Activo	\N	\N	PT CRUISER	8
78	Activo	\N	\N	SEBRIG	8
79	Activo	\N	\N	BERLINGO	9
80	Activo	\N	\N	C3	9
81	Activo	\N	\N	C3 AIRCROSS	9
82	Activo	\N	\N	C3 PICASSO	9
83	Activo	\N	\N	C4 AIRCROSS	9
84	Activo	\N	\N	C4 LOUNGE	9
85	Activo	\N	\N	C4 PICASSO	9
86	Activo	\N	\N	C4 GRAND PICASSO	9
87	Activo	\N	\N	C5	9
88	Activo	\N	\N	C6	9
89	Activo	\N	\N	DS3	9
90	Activo	\N	\N	DS4	9
91	Activo	\N	\N	C15	9
92	Activo	\N	\N	JUMPER	9
93	Activo	\N	\N	SAXO	9
94	Activo	\N	\N	XSARA	9
95	Activo	\N	\N	XSARA PICASSO	9
96	Activo	\N	\N	PICK-UP	10
97	Activo	\N	\N	LANOS	11
98	Activo	\N	\N	LEGANZA	11
99	Activo	\N	\N	NUBIRA	11
100	Activo	\N	\N	MATIZ	11
101	Activo	\N	\N	TACUMA	11
102	Activo	\N	\N	DAMAS	11
103	Activo	\N	\N	LABO	11
104	Activo	\N	\N	MOVE	12
105	Activo	\N	\N	ROCKY	12
106	Activo	\N	\N	SIRION	12
107	Activo	\N	\N	TERIOS	12
108	Activo	\N	\N	MIRA	12
109	Activo	\N	\N	JOURNEY	13
110	Activo	\N	\N	RAM	13
111	Activo	\N	\N	360	14
112	Activo	\N	\N	430	14
113	Activo	\N	\N	456	14
114	Activo	\N	\N	575	14
115	Activo	\N	\N	599	14
116	Activo	\N	\N	612	14
117	Activo	\N	\N	CALIFORNIA	14
118	Activo	\N	\N	SUPERAMERICA	14
119	Activo	\N	\N	500	15
120	Activo	\N	\N	BRAVA	15
121	Activo	\N	\N	BRAVO	15
122	Activo	\N	\N	DOBLO	15
123	Activo	\N	\N	DUCATO	15
124	Activo	\N	\N	FIORINO	15
125	Activo	\N	\N	FIORINO QUBO	15
126	Activo	\N	\N	IDEA	15
127	Activo	\N	\N	LINEA	15
128	Activo	\N	\N	MAREA	15
129	Activo	\N	\N	PALIO	15
130	Activo	\N	\N	PALIO ADVENTURE	15
131	Activo	\N	\N	PUNTO	15
132	Activo	\N	\N	QUBO	15
133	Activo	\N	\N	SIENA	15
134	Activo	\N	\N	GRAND SIENA	15
135	Activo	\N	\N	STILO	15
136	Activo	\N	\N	STRADA	15
137	Activo	\N	\N	UNO	15
138	Activo	\N	\N	UNO EVO	15
139	Activo	\N	\N	COURIER	16
140	Activo	\N	\N	ECOSPORT	16
141	Activo	\N	\N	ECOSPORT KD	16
142	Activo	\N	\N	ESCAPE	16
143	Activo	\N	\N	F100	16
144	Activo	\N	\N	FIESTA KD	16
145	Activo	\N	\N	FIESTA	16
146	Activo	\N	\N	FOCUS	16
147	Activo	\N	\N	FOCUS III	16
148	Activo	\N	\N	KA	16
149	Activo	\N	\N	KUGA	16
150	Activo	\N	\N	MONDEO	16
151	Activo	\N	\N	RANGER	16
152	Activo	\N	\N	S-MAX	16
153	Activo	\N	\N	TRANSIT	16
154	Activo	\N	\N	EXCEED	17
155	Activo	\N	\N	HB	18
156	Activo	\N	\N	ACCORD	19
157	Activo	\N	\N	CITY	19
158	Activo	\N	\N	CIVIC	19
159	Activo	\N	\N	CRV	19
160	Activo	\N	\N	FIT	19
161	Activo	\N	\N	HRV	19
162	Activo	\N	\N	LEGEND	19
163	Activo	\N	\N	PILOT	19
164	Activo	\N	\N	STREAM	19
165	Activo	\N	\N	ACCENT	20
166	Activo	\N	\N	ATOS PRIME	20
167	Activo	\N	\N	COUPE	20
168	Activo	\N	\N	ELANTRA	20
169	Activo	\N	\N	I 10	20
170	Activo	\N	\N	I 30	20
171	Activo	\N	\N	MATRIX	20
172	Activo	\N	\N	SANTA FE	20
173	Activo	\N	\N	SONATA	20
174	Activo	\N	\N	TERRACAN	20
175	Activo	\N	\N	TRAJET	20
176	Activo	\N	\N	TUCSON	20
177	Activo	\N	\N	VELOSTER	20
178	Activo	\N	\N	VERACRUZ	20
179	Activo	\N	\N	AMIGO	21
180	Activo	\N	\N	PICK-UP CABIAN SIMPLE	21
181	Activo	\N	\N	PICK-UP SPACE CAB	21
182	Activo	\N	\N	PICK-UP CABINA DOBLE	21
183	Activo	\N	\N	TROOPER	21
184	Activo	\N	\N	X-TYPE	22
185	Activo	\N	\N	XF	22
186	Activo	\N	\N	F-TYPE	22
187	Activo	\N	\N	S-TYPE	22
188	Activo	\N	\N	XJ	22
189	Activo	\N	\N	XK	22
190	Activo	\N	\N	CHEROKEE	23
191	Activo	\N	\N	COMPASS	23
192	Activo	\N	\N	GRAND CHEROKEE	23
193	Activo	\N	\N	PATRIOT	23
194	Activo	\N	\N	WRANGLER	23
195	Activo	\N	\N	CARENS	24
196	Activo	\N	\N	CARNIVAL	24
197	Activo	\N	\N	CERATO	24
198	Activo	\N	\N	MAGENTIS	24
199	Activo	\N	\N	MOHAVE	24
200	Activo	\N	\N	OPIRUS	24
201	Activo	\N	\N	PICANTO	24
202	Activo	\N	\N	RIO	24
203	Activo	\N	\N	RONDO	24
204	Activo	\N	\N	SPORTAGE	24
205	Activo	\N	\N	GRAND SPORTAGE	24
206	Activo	\N	\N	SORENTO	24
207	Activo	\N	\N	SOUL	24
208	Activo	\N	\N	PREGIO	24
209	Activo	\N	\N	AFALINA	25
210	Activo	\N	\N	SAMARA	25
211	Activo	\N	\N	DEFENDER	26
212	Activo	\N	\N	DISCOVERY	26
213	Activo	\N	\N	FREELANDER	26
214	Activo	\N	\N	RANGE ROVER	26
215	Activo	\N	\N	LS	27
216	Activo	\N	\N	GS	27
217	Activo	\N	\N	IS	27
218	Activo	\N	\N	QUATTROPORTE	28
219	Activo	\N	\N	COUPE SPORT	28
220	Activo	\N	\N	GRAND TURISMO	28
221	Activo	\N	\N	SPYDER	28
222	Activo	\N	\N	323	29
223	Activo	\N	\N	626	29
224	Activo	\N	\N	MPV	29
225	Activo	\N	\N	B 2500	29
226	Activo	\N	\N	B 2900	29
227	Activo	\N	\N	AMG	30
228	Activo	\N	\N	CLASE A	30
229	Activo	\N	\N	CLASE B	30
230	Activo	\N	\N	CLASE C	30
231	Activo	\N	\N	CLASE CL	30
232	Activo	\N	\N	CLASE CLA	30
233	Activo	\N	\N	CLASE CLC	30
234	Activo	\N	\N	CLASE CLK	30
235	Activo	\N	\N	CLASE CLS	30
236	Activo	\N	\N	CLASE E	30
237	Activo	\N	\N	CLASE G	30
238	Activo	\N	\N	CLASE GL	30
239	Activo	\N	\N	CLASE ML	30
240	Activo	\N	\N	CLASE S	30
241	Activo	\N	\N	CLASE SL	30
242	Activo	\N	\N	CLASE SLK	30
243	Activo	\N	\N	VIANO	30
244	Activo	\N	\N	MGF	31
245	Activo	\N	\N	COOPER	32
246	Activo	\N	\N	CANTER	33
247	Activo	\N	\N	L-200	33
248	Activo	\N	\N	LANCER	33
249	Activo	\N	\N	MONTERO	33
250	Activo	\N	\N	NATIVA	33
251	Activo	\N	\N	OUTLANDER	33
252	Activo	\N	\N	350	34
253	Activo	\N	\N	370Z	34
254	Activo	\N	\N	PATHFINDER	34
255	Activo	\N	\N	FRONTIER	34
256	Activo	\N	\N	MARCH	34
257	Activo	\N	\N	MURANO	34
258	Activo	\N	\N	NP300	34
259	Activo	\N	\N	PICK-UP SPORT	34
260	Activo	\N	\N	SENTRA	34
261	Activo	\N	\N	TEANA	34
262	Activo	\N	\N	TERRANO II	34
263	Activo	\N	\N	TIIDA	34
264	Activo	\N	\N	VERSA	34
265	Activo	\N	\N	X-TERRA	34
266	Activo	\N	\N	X-TRAIL	34
267	Activo	\N	\N	106	35
268	Activo	\N	\N	206	35
269	Activo	\N	\N	207	35
270	Activo	\N	\N	208	35
271	Activo	\N	\N	306	35
272	Activo	\N	\N	307	35
273	Activo	\N	\N	308	35
274	Activo	\N	\N	3008	35
275	Activo	\N	\N	405	35
276	Activo	\N	\N	406	35
277	Activo	\N	\N	407	35
278	Activo	\N	\N	408	35
279	Activo	\N	\N	4008	35
280	Activo	\N	\N	508	35
281	Activo	\N	\N	5008	35
282	Activo	\N	\N	607	35
283	Activo	\N	\N	806	35
284	Activo	\N	\N	807	35
285	Activo	\N	\N	RCZ	35
286	Activo	\N	\N	EXPERT	35
287	Activo	\N	\N	HOGGAR	35
288	Activo	\N	\N	PARTNER	35
289	Activo	\N	\N	BOXER	35
290	Activo	\N	\N	911	36
291	Activo	\N	\N	BOXSTER	36
292	Activo	\N	\N	CAYENNE	36
293	Activo	\N	\N	CAYMAN	36
294	Activo	\N	\N	PANAMERA	36
295	Activo	\N	\N	1500	37
296	Activo	\N	\N	2500	37
297	Activo	\N	\N	CLIO MIO	38
298	Activo	\N	\N	CLIO L/NUEVA	38
299	Activo	\N	\N	CLIO 2	38
300	Activo	\N	\N	DUSTER	38
301	Activo	\N	\N	FLUENCE	38
302	Activo	\N	\N	KANGOO	38
303	Activo	\N	\N	KANGOO FURGON	38
304	Activo	\N	\N	KOLEOS	38
305	Activo	\N	\N	LAGUNA	38
306	Activo	\N	\N	LATITUDE	38
307	Activo	\N	\N	LOGAN	38
308	Activo	\N	\N	MEGANE	38
309	Activo	\N	\N	MEGANE II	38
310	Activo	\N	\N	MEGANE III	38
311	Activo	\N	\N	SANDERO	38
312	Activo	\N	\N	SANDERO STEPWAY	38
313	Activo	\N	\N	SCENIC	38
314	Activo	\N	\N	SYMBOL	38
315	Activo	\N	\N	TWINGO	38
316	Activo	\N	\N	TRAFIC	38
317	Activo	\N	\N	MASTER	38
318	Activo	\N	\N	LINEA 25	39
319	Activo	\N	\N	LINEA 45	39
320	Activo	\N	\N	LINEA 75	39
321	Activo	\N	\N	LINEA 9.3	39
322	Activo	\N	\N	LINEA 9.5	39
323	Activo	\N	\N	ALHAMBRA	40
324	Activo	\N	\N	ALTEA	40
325	Activo	\N	\N	CORDOBA	40
326	Activo	\N	\N	IBIZA	40
327	Activo	\N	\N	INCA FURGON	40
328	Activo	\N	\N	LEON	40
329	Activo	\N	\N	TOLEDO	40
330	Activo	\N	\N	FORTWO	41
331	Activo	\N	\N	ACTYON	42
332	Activo	\N	\N	KORANDO	42
333	Activo	\N	\N	KYRON	42
334	Activo	\N	\N	ISTANA	42
335	Activo	\N	\N	MUSSO	42
336	Activo	\N	\N	REXTON	42
337	Activo	\N	\N	STAVIC	42
338	Activo	\N	\N	IMPREZA	43
339	Activo	\N	\N	LEGACY	43
340	Activo	\N	\N	OUTBACK	43
341	Activo	\N	\N	TRIBECA	43
342	Activo	\N	\N	XV	43
343	Activo	\N	\N	FORESTER	43
344	Activo	\N	\N	FUN	44
345	Activo	\N	\N	GRAND VITARA	44
346	Activo	\N	\N	SWIFT	44
347	Activo	\N	\N	JIMNY	44
348	Activo	\N	\N	207 TELCOLINE	45
349	Activo	\N	\N	SUMO	46
350	Activo	\N	\N	86	47
351	Activo	\N	\N	AVENSIS	47
352	Activo	\N	\N	CAMRY	47
353	Activo	\N	\N	COROLLA	47
354	Activo	\N	\N	CORONA	47
355	Activo	\N	\N	ETIOS	47
356	Activo	\N	\N	ETIOS CROSS	47
357	Activo	\N	\N	HILUX	47
358	Activo	\N	\N	LAND CRUISER	47
359	Activo	\N	\N	PRIUS	47
360	Activo	\N	\N	RAV 4	47
361	Activo	\N	\N	AMAROK	48
362	Activo	\N	\N	BORA	48
363	Activo	\N	\N	CADDY	48
364	Activo	\N	\N	CROSSFOX	48
365	Activo	\N	\N	FOX	48
366	Activo	\N	\N	GOL	48
367	Activo	\N	\N	GOL TREND	48
368	Activo	\N	\N	GOLF	48
369	Activo	\N	\N	MULTIVAN	48
370	Activo	\N	\N	THE BEETLE	48
371	Activo	\N	\N	NEW BEETLE	48
372	Activo	\N	\N	PASSAT	48
373	Activo	\N	\N	CC	48
374	Activo	\N	\N	POLO	48
375	Activo	\N	\N	SANTANA	48
376	Activo	\N	\N	SAVEIRO	48
377	Activo	\N	\N	SCIROCCO	48
378	Activo	\N	\N	SHARAN	48
379	Activo	\N	\N	SURAN	48
380	Activo	\N	\N	TIGUAN	48
381	Activo	\N	\N	TOUAREG	48
382	Activo	\N	\N	TRANSPORTER	48
383	Activo	\N	\N	UP	48
384	Activo	\N	\N	VENTO	48
385	Activo	\N	\N	VOYAGE	48
386	Activo	\N	\N	C30	49
387	Activo	\N	\N	C70	49
388	Activo	\N	\N	S40	49
389	Activo	\N	\N	S60	49
390	Activo	\N	\N	S80	49
391	Activo	\N	\N	V40	49
392	Activo	\N	\N	V50	49
393	Activo	\N	\N	V60	49
394	Activo	\N	\N	V70	49
395	Activo	\N	\N	XC60	49
396	Activo	\N	\N	XC70	49
397	Activo	\N	\N	XC90	49
\.


--
-- TOC entry 3588 (class 0 OID 0)
-- Dependencies: 309
-- Name: modelo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('modelo_id_seq', 1, false);


--
-- TOC entry 3348 (class 0 OID 96321)
-- Dependencies: 220
-- Data for Name: motivo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY motivo (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	No Presta el Servivio Solicitado	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Tecnologias o Herramientas No Disponible	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Marca de Vehiculo Que No Se Presta Servicio	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	El Cliente No Acepto el Precio de Presupuesto	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	No Se Encuentra Disponible el Servicio Solicitado	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	El Taller Con Cuenta con Espacio Disponible En Pocos Dias	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	El Cliente No Se Intereso en El Presupuesto Ofertado	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	El Tiempo de Vigencia Excedido	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	La Garantia No Cubre El Reclamo Realizado	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
10	No Aplica El Reclamo, No Es Responsabilidad del Taller	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
11	No Aplica El Reclamo, Servicio No Realizado en el Taller	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3589 (class 0 OID 0)
-- Dependencies: 310
-- Name: motivo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('motivo_id_seq', 11, true);


--
-- TOC entry 3349 (class 0 OID 96329)
-- Dependencies: 221
-- Data for Name: motor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY motor (id, descripcion, estatus, fechacreacion, fechamodificacion, serial, idtipomotor) FROM stdin;
1	4 cilindros	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	2548554	1
2	8 cilindros	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	bfrs1542	1
3	Audi FSI	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	fr25458	1
4	AFerrari V12	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	jfys5844	1
5	Ferrari V8	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	kfe658	1
6	Fiat Twin Air	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	kjyhh584	1
7	Ford EcoBoost	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	rdef7847	1
8	Honda IMA	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	ytrf4875	1
9	Honda J series	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	kduhf55	1
10	Honda VTEC	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	hdtau258	1
11	 Volkswagen TSI TwinCharger	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	jyt8475	1
12	Volkswagen W8	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	drehs874	1
13	3 Cilindros	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	dfrs874	2
\.


--
-- TOC entry 3590 (class 0 OID 0)
-- Dependencies: 311
-- Name: motor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('motor_id_seq', 13, true);


--
-- TOC entry 3350 (class 0 OID 96337)
-- Dependencies: 222
-- Data for Name: noticia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY noticia (id, descripcion, estatus, fechacreacion, fechamodificacion, titulo, urlimagen) FROM stdin;
1	<p>Fue el presidente ejecutivo de Volvo en Norteam&eacute;rica, Lex&nbsp; Kerssemakers, quien confirm&oacute; a los medios de comunicaci&oacute;n que <strong>la marca sueca lanzar&aacute; un modelo 100% el&eacute;ctrico en 2019</strong>.</p>\n\n<p>Sin entrar en grandes detalles, se sabe que la marca quiere posicionarlo en la parte baja del mercado, con <strong>un precio entre US$35.000 y US$40.000</strong>, en l&iacute;nea con lo que valen el Chevrolet Bolt EV y el Testa Model 3, que saldr&aacute; a la venta a mediados de este a&ntilde;o.</p>	Activo	2017-03-22 09:32:57.009	2017-03-22 09:32:57.009	Volvo tendrá un carro 100% eléctrico para 2019	/media/images/noticias/img_noticia1850121908714935255.jpeg
2	<p>En l&iacute;nea con lo ocurrido con el <a href="http://especiales.ve.autocosmos.com/autoshowdeparis/noticias/2016/10/01/audi-rs3-sedan-una-bestia-de-400-caballos" target="_blank">RS3</a>, Audi tambi&eacute;n decidi&oacute; renovar a uno de los modelos m&aacute;s impresionantes de su gama, <strong>el RS5</strong>. <strong>Esta nueva generaci&oacute;n nos muestra lo &uacute;ltimo que sali&oacute; de las plumas</strong><strong>&nbsp;de los dise&ntilde;adores de la firma</strong>, con una nueva est&eacute;tica que lo diferencia bastante del modelo que reemplaza.</p>	Activo	2017-03-22 09:36:17.314	2017-03-22 09:36:17.314	Audi RS5 2018, un coupé muy rabioso	/media/images/noticias/img_noticia1809516082413019013.jpeg
3	<p>As&iacute; como muchas automotrices miran a China con cari&ntilde;o, la <strong>India es otro gran mercado que abre un abanico de posibilidades en cuanto a inversiones y proyectos</strong>. En virtud de esto, el <strong>Grupo VW, el de mayor volumen de ventas del mundo,</strong> anunci&oacute; junto a <strong>Tata Motors</strong> una sociedad para la producci&oacute;n y comercializaci&oacute;n de autos en mercados emergentes, tal el caso de el gigante asi&aacute;tico.<strong> </strong></p>	Activo	2017-03-22 09:36:54.404	2017-03-22 09:36:54.404	Tata Motors y Volkswagen realizan una alianza estratégica	/media/images/noticias/img_noticia2883248999693323641.jpeg
\.


--
-- TOC entry 3591 (class 0 OID 0)
-- Dependencies: 386
-- Name: noticia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('noticia_id_seq', 3, true);


--
-- TOC entry 3351 (class 0 OID 96347)
-- Dependencies: 223
-- Data for Name: notificacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY notificacion (id, descripcion, estatus, fechacreacion, nombre, ideventualidad, idordenentrega, idpresupuesto, idpromocion, idtiponotificacion) FROM stdin;
\.


--
-- TOC entry 3592 (class 0 OID 0)
-- Dependencies: 312
-- Name: notificacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notificacion_id_seq', 1, false);


--
-- TOC entry 3352 (class 0 OID 96355)
-- Dependencies: 224
-- Data for Name: notificacionusuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY notificacionusuario (id, estatus, fechacreacion, fechamodificacion, idnotificacion, idusuario) FROM stdin;
\.


--
-- TOC entry 3593 (class 0 OID 0)
-- Dependencies: 313
-- Name: notificacionusuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notificacionusuario_id_seq', 1, false);


--
-- TOC entry 3353 (class 0 OID 96360)
-- Dependencies: 225
-- Data for Name: ocupacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ocupacion (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Profesor	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Doctor	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Deportista	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Conductor	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Gamer	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Artista	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Mecnico	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	Comerciante	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	Visitador medico	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
10	Artesano	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
11	Ama de Casa	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3594 (class 0 OID 0)
-- Dependencies: 314
-- Name: ocupacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ocupacion_id_seq', 11, true);


--
-- TOC entry 3354 (class 0 OID 96370)
-- Dependencies: 226
-- Data for Name: ordenentrega; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ordenentrega (id, descripcion, estado, estatus, fechacreacion, fechamodificacion, idgarantia, idprueba) FROM stdin;
\.


--
-- TOC entry 3595 (class 0 OID 0)
-- Dependencies: 315
-- Name: ordenentrega_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ordenentrega_id_seq', 1, false);


--
-- TOC entry 3355 (class 0 OID 96380)
-- Dependencies: 227
-- Data for Name: ordenservicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ordenservicio (id, descripcion, estado, estatus, fechacreacion, fechamodificacion, idpresupuesto) FROM stdin;
\.


--
-- TOC entry 3596 (class 0 OID 0)
-- Dependencies: 316
-- Name: ordenservicio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ordenservicio_id_seq', 1, false);


--
-- TOC entry 3356 (class 0 OID 96388)
-- Dependencies: 228
-- Data for Name: ordenserviciousuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ordenserviciousuario (id, estatus, fechacreacion, fechamodificacion, idordenservicio, idusuario) FROM stdin;
\.


--
-- TOC entry 3597 (class 0 OID 0)
-- Dependencies: 317
-- Name: ordenserviciousuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ordenserviciousuario_id_seq', 1, false);


--
-- TOC entry 3357 (class 0 OID 96393)
-- Dependencies: 229
-- Data for Name: pasatiempo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pasatiempo (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Peliculas	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Carreras de Piques	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Animales	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Videojuegos	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Leer	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Cantar	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Trabajo con madera	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	Pintar	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	Tocar Guitarra	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
10	Fotografia	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
11	Pescar	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
14	Tocar guitarra	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3598 (class 0 OID 0)
-- Dependencies: 318
-- Name: pasatiempo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pasatiempo_id_seq', 15, true);


--
-- TOC entry 3358 (class 0 OID 96403)
-- Dependencies: 230
-- Data for Name: permiso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY permiso (id, acceso, estatus, fechacreaccion, fechamodificacion, idaccion, idfuncion, idgrupo) FROM stdin;
1	f	Activo	2017-03-22 08:50:08.952	\N	1	1	2
2	f	Activo	2017-03-22 08:50:08.952	\N	2	1	2
3	f	Activo	2017-03-22 08:50:08.952	\N	3	1	2
4	f	Activo	2017-03-22 08:50:08.952	\N	4	1	2
5	f	Activo	2017-03-22 08:50:08.952	\N	6	1	2
6	f	Activo	2017-03-22 08:50:08.953	\N	1	5	2
7	f	Activo	2017-03-22 08:50:08.953	\N	2	5	2
8	f	Activo	2017-03-22 08:50:08.953	\N	3	5	2
9	f	Activo	2017-03-22 08:50:08.953	\N	4	5	2
10	f	Activo	2017-03-22 08:50:08.953	\N	6	5	2
11	f	Activo	2017-03-22 08:50:08.953	\N	1	6	2
12	f	Activo	2017-03-22 08:50:08.953	\N	2	6	2
13	f	Activo	2017-03-22 08:50:08.953	\N	3	6	2
14	f	Activo	2017-03-22 08:50:08.953	\N	4	6	2
15	f	Activo	2017-03-22 08:50:08.953	\N	6	6	2
16	f	Activo	2017-03-22 08:50:08.953	\N	1	7	2
17	f	Activo	2017-03-22 08:50:08.953	\N	2	7	2
18	f	Activo	2017-03-22 08:50:08.953	\N	3	7	2
19	f	Activo	2017-03-22 08:50:08.953	\N	4	7	2
20	f	Activo	2017-03-22 08:50:08.953	\N	6	7	2
21	f	Activo	2017-03-22 08:50:08.953	\N	1	8	2
22	f	Activo	2017-03-22 08:50:08.953	\N	2	8	2
23	f	Activo	2017-03-22 08:50:08.953	\N	3	8	2
24	f	Activo	2017-03-22 08:50:08.953	\N	4	8	2
25	f	Activo	2017-03-22 08:50:08.953	\N	6	8	2
26	f	Activo	2017-03-22 08:50:08.953	\N	1	9	2
27	f	Activo	2017-03-22 08:50:08.953	\N	2	9	2
28	f	Activo	2017-03-22 08:50:08.953	\N	3	9	2
29	f	Activo	2017-03-22 08:50:08.953	\N	4	9	2
30	f	Activo	2017-03-22 08:50:08.953	\N	6	9	2
31	f	Activo	2017-03-22 08:50:08.953	\N	1	10	2
32	f	Activo	2017-03-22 08:50:08.953	\N	2	10	2
33	f	Activo	2017-03-22 08:50:08.953	\N	3	10	2
34	f	Activo	2017-03-22 08:50:08.953	\N	4	10	2
35	f	Activo	2017-03-22 08:50:08.953	\N	6	10	2
36	f	Activo	2017-03-22 08:50:08.953	\N	1	11	2
37	f	Activo	2017-03-22 08:50:08.953	\N	2	11	2
38	f	Activo	2017-03-22 08:50:08.953	\N	3	11	2
39	f	Activo	2017-03-22 08:50:08.953	\N	4	11	2
40	f	Activo	2017-03-22 08:50:08.953	\N	6	11	2
41	f	Activo	2017-03-22 08:50:08.953	\N	1	12	2
42	f	Activo	2017-03-22 08:50:08.953	\N	2	12	2
43	f	Activo	2017-03-22 08:50:08.953	\N	3	12	2
44	f	Activo	2017-03-22 08:50:08.953	\N	4	12	2
45	f	Activo	2017-03-22 08:50:08.953	\N	6	12	2
46	f	Activo	2017-03-22 08:50:08.953	\N	1	13	2
47	f	Activo	2017-03-22 08:50:08.953	\N	2	13	2
48	f	Activo	2017-03-22 08:50:08.953	\N	3	13	2
49	f	Activo	2017-03-22 08:50:08.953	\N	4	13	2
50	f	Activo	2017-03-22 08:50:08.953	\N	6	13	2
51	f	Activo	2017-03-22 08:50:08.953	\N	1	15	2
52	f	Activo	2017-03-22 08:50:08.953	\N	2	15	2
53	f	Activo	2017-03-22 08:50:08.953	\N	3	15	2
54	f	Activo	2017-03-22 08:50:08.953	\N	4	15	2
55	f	Activo	2017-03-22 08:50:08.953	\N	6	15	2
56	f	Activo	2017-03-22 08:50:08.953	\N	1	16	2
57	f	Activo	2017-03-22 08:50:08.953	\N	2	16	2
58	f	Activo	2017-03-22 08:50:08.953	\N	3	16	2
59	f	Activo	2017-03-22 08:50:08.953	\N	4	16	2
60	f	Activo	2017-03-22 08:50:08.953	\N	6	16	2
61	f	Activo	2017-03-22 08:50:08.953	\N	1	17	2
62	f	Activo	2017-03-22 08:50:08.953	\N	2	17	2
63	f	Activo	2017-03-22 08:50:08.953	\N	3	17	2
64	f	Activo	2017-03-22 08:50:08.953	\N	4	17	2
65	f	Activo	2017-03-22 08:50:08.953	\N	6	17	2
66	f	Activo	2017-03-22 08:50:08.953	\N	1	18	2
67	f	Activo	2017-03-22 08:50:08.953	\N	2	18	2
68	f	Activo	2017-03-22 08:50:08.953	\N	3	18	2
69	f	Activo	2017-03-22 08:50:08.953	\N	4	18	2
70	f	Activo	2017-03-22 08:50:08.953	\N	6	18	2
71	f	Activo	2017-03-22 08:50:08.953	\N	1	19	2
72	f	Activo	2017-03-22 08:50:08.953	\N	2	19	2
73	f	Activo	2017-03-22 08:50:08.953	\N	3	19	2
74	f	Activo	2017-03-22 08:50:08.953	\N	4	19	2
75	f	Activo	2017-03-22 08:50:08.953	\N	6	19	2
76	f	Activo	2017-03-22 08:50:08.953	\N	1	20	2
77	f	Activo	2017-03-22 08:50:08.953	\N	2	20	2
78	f	Activo	2017-03-22 08:50:08.953	\N	3	20	2
79	f	Activo	2017-03-22 08:50:08.953	\N	4	20	2
80	f	Activo	2017-03-22 08:50:08.953	\N	6	20	2
81	f	Activo	2017-03-22 08:50:08.953	\N	1	21	2
82	f	Activo	2017-03-22 08:50:08.953	\N	2	21	2
83	f	Activo	2017-03-22 08:50:08.953	\N	3	21	2
84	f	Activo	2017-03-22 08:50:08.953	\N	4	21	2
85	f	Activo	2017-03-22 08:50:08.953	\N	6	21	2
86	f	Activo	2017-03-22 08:50:08.953	\N	1	22	2
87	f	Activo	2017-03-22 08:50:08.953	\N	2	22	2
88	f	Activo	2017-03-22 08:50:08.953	\N	3	22	2
89	f	Activo	2017-03-22 08:50:08.953	\N	4	22	2
90	f	Activo	2017-03-22 08:50:08.953	\N	6	22	2
91	f	Activo	2017-03-22 08:50:08.953	\N	1	23	2
92	f	Activo	2017-03-22 08:50:08.953	\N	2	23	2
93	f	Activo	2017-03-22 08:50:08.953	\N	3	23	2
94	f	Activo	2017-03-22 08:50:08.953	\N	4	23	2
95	f	Activo	2017-03-22 08:50:08.953	\N	6	23	2
96	f	Activo	2017-03-22 08:50:08.953	\N	1	24	2
97	f	Activo	2017-03-22 08:50:08.953	\N	2	24	2
98	f	Activo	2017-03-22 08:50:08.953	\N	3	24	2
99	f	Activo	2017-03-22 08:50:08.953	\N	4	24	2
100	f	Activo	2017-03-22 08:50:08.953	\N	6	24	2
101	f	Activo	2017-03-22 08:50:08.953	\N	1	25	2
102	f	Activo	2017-03-22 08:50:08.953	\N	2	25	2
103	f	Activo	2017-03-22 08:50:08.953	\N	3	25	2
104	f	Activo	2017-03-22 08:50:08.953	\N	4	25	2
105	f	Activo	2017-03-22 08:50:08.953	\N	6	25	2
106	f	Activo	2017-03-22 08:50:08.953	\N	1	26	2
107	f	Activo	2017-03-22 08:50:08.953	\N	2	26	2
108	f	Activo	2017-03-22 08:50:08.953	\N	3	26	2
109	f	Activo	2017-03-22 08:50:08.953	\N	4	26	2
110	f	Activo	2017-03-22 08:50:08.953	\N	6	26	2
111	f	Activo	2017-03-22 08:50:08.953	\N	1	27	2
112	f	Activo	2017-03-22 08:50:08.953	\N	2	27	2
113	f	Activo	2017-03-22 08:50:08.953	\N	3	27	2
114	f	Activo	2017-03-22 08:50:08.953	\N	4	27	2
115	f	Activo	2017-03-22 08:50:08.953	\N	6	27	2
116	f	Activo	2017-03-22 08:50:08.953	\N	1	28	2
117	f	Activo	2017-03-22 08:50:08.953	\N	2	28	2
118	f	Activo	2017-03-22 08:50:08.953	\N	3	28	2
119	f	Activo	2017-03-22 08:50:08.953	\N	4	28	2
120	f	Activo	2017-03-22 08:50:08.953	\N	6	28	2
121	f	Activo	2017-03-22 08:50:08.953	\N	1	29	2
122	f	Activo	2017-03-22 08:50:08.953	\N	2	29	2
123	f	Activo	2017-03-22 08:50:08.953	\N	3	29	2
124	f	Activo	2017-03-22 08:50:08.953	\N	4	29	2
125	f	Activo	2017-03-22 08:50:08.953	\N	6	29	2
126	f	Activo	2017-03-22 08:50:08.953	\N	1	30	2
127	f	Activo	2017-03-22 08:50:08.953	\N	2	30	2
128	f	Activo	2017-03-22 08:50:08.953	\N	3	30	2
129	f	Activo	2017-03-22 08:50:08.953	\N	4	30	2
130	f	Activo	2017-03-22 08:50:08.953	\N	6	30	2
131	f	Activo	2017-03-22 08:50:08.953	\N	1	31	2
132	f	Activo	2017-03-22 08:50:08.953	\N	2	31	2
133	f	Activo	2017-03-22 08:50:08.953	\N	3	31	2
134	f	Activo	2017-03-22 08:50:08.953	\N	4	31	2
135	f	Activo	2017-03-22 08:50:08.953	\N	6	31	2
136	f	Activo	2017-03-22 08:50:08.953	\N	1	32	2
137	f	Activo	2017-03-22 08:50:08.953	\N	2	32	2
138	f	Activo	2017-03-22 08:50:08.953	\N	3	32	2
139	f	Activo	2017-03-22 08:50:08.953	\N	4	32	2
140	f	Activo	2017-03-22 08:50:08.953	\N	6	32	2
141	f	Activo	2017-03-22 08:50:08.953	\N	1	33	2
142	f	Activo	2017-03-22 08:50:08.953	\N	2	33	2
143	f	Activo	2017-03-22 08:50:08.953	\N	3	33	2
144	f	Activo	2017-03-22 08:50:08.953	\N	4	33	2
145	f	Activo	2017-03-22 08:50:08.953	\N	6	33	2
146	f	Activo	2017-03-22 08:50:08.953	\N	1	34	2
147	f	Activo	2017-03-22 08:50:08.953	\N	2	34	2
148	f	Activo	2017-03-22 08:50:08.953	\N	3	34	2
149	f	Activo	2017-03-22 08:50:08.953	\N	4	34	2
150	f	Activo	2017-03-22 08:50:08.953	\N	6	34	2
151	f	Activo	2017-03-22 08:50:08.953	\N	1	35	2
152	f	Activo	2017-03-22 08:50:08.953	\N	2	35	2
153	f	Activo	2017-03-22 08:50:08.953	\N	3	35	2
154	f	Activo	2017-03-22 08:50:08.953	\N	4	35	2
155	f	Activo	2017-03-22 08:50:08.953	\N	6	35	2
156	f	Activo	2017-03-22 08:50:08.953	\N	1	36	2
157	f	Activo	2017-03-22 08:50:08.953	\N	2	36	2
158	f	Activo	2017-03-22 08:50:08.953	\N	3	36	2
159	f	Activo	2017-03-22 08:50:08.953	\N	4	36	2
160	f	Activo	2017-03-22 08:50:08.953	\N	6	36	2
161	f	Activo	2017-03-22 08:50:08.953	\N	1	37	2
162	f	Activo	2017-03-22 08:50:08.953	\N	2	37	2
163	f	Activo	2017-03-22 08:50:08.953	\N	3	37	2
164	f	Activo	2017-03-22 08:50:08.953	\N	4	37	2
165	f	Activo	2017-03-22 08:50:08.953	\N	6	37	2
166	f	Activo	2017-03-22 08:50:08.953	\N	1	39	2
167	f	Activo	2017-03-22 08:50:08.953	\N	2	39	2
168	f	Activo	2017-03-22 08:50:08.953	\N	3	39	2
169	f	Activo	2017-03-22 08:50:08.953	\N	4	39	2
170	f	Activo	2017-03-22 08:50:08.953	\N	6	39	2
171	f	Activo	2017-03-22 08:50:08.953	\N	1	41	2
172	f	Activo	2017-03-22 08:50:08.953	\N	2	41	2
173	f	Activo	2017-03-22 08:50:08.953	\N	3	41	2
174	f	Activo	2017-03-22 08:50:08.953	\N	4	41	2
175	f	Activo	2017-03-22 08:50:08.953	\N	6	41	2
176	f	Activo	2017-03-22 08:50:08.953	\N	1	42	2
177	f	Activo	2017-03-22 08:50:08.953	\N	2	42	2
178	f	Activo	2017-03-22 08:50:08.953	\N	3	42	2
179	f	Activo	2017-03-22 08:50:08.953	\N	4	42	2
180	f	Activo	2017-03-22 08:50:08.953	\N	6	42	2
181	f	Activo	2017-03-22 08:50:08.953	\N	1	43	2
182	f	Activo	2017-03-22 08:50:08.953	\N	2	43	2
183	f	Activo	2017-03-22 08:50:08.953	\N	3	43	2
184	f	Activo	2017-03-22 08:50:08.953	\N	4	43	2
185	f	Activo	2017-03-22 08:50:08.953	\N	6	43	2
186	f	Activo	2017-03-22 08:50:08.953	\N	1	45	2
187	f	Activo	2017-03-22 08:50:08.953	\N	2	45	2
188	f	Activo	2017-03-22 08:50:08.953	\N	3	45	2
189	f	Activo	2017-03-22 08:50:08.953	\N	4	45	2
190	f	Activo	2017-03-22 08:50:08.953	\N	6	45	2
191	f	Activo	2017-03-22 08:50:08.953	\N	1	46	2
192	f	Activo	2017-03-22 08:50:08.953	\N	2	46	2
193	f	Activo	2017-03-22 08:50:08.953	\N	3	46	2
194	f	Activo	2017-03-22 08:50:08.953	\N	4	46	2
195	f	Activo	2017-03-22 08:50:08.953	\N	6	46	2
196	f	Activo	2017-03-22 08:50:08.953	\N	1	47	2
197	f	Activo	2017-03-22 08:50:08.953	\N	2	47	2
198	f	Activo	2017-03-22 08:50:08.953	\N	3	47	2
199	f	Activo	2017-03-22 08:50:08.953	\N	4	47	2
200	f	Activo	2017-03-22 08:50:08.953	\N	6	47	2
201	f	Activo	2017-03-22 08:50:08.953	\N	1	49	2
202	f	Activo	2017-03-22 08:50:08.953	\N	2	49	2
203	f	Activo	2017-03-22 08:50:08.953	\N	3	49	2
204	f	Activo	2017-03-22 08:50:08.953	\N	4	49	2
205	f	Activo	2017-03-22 08:50:08.953	\N	6	49	2
206	f	Activo	2017-03-22 08:50:08.953	\N	1	50	2
207	f	Activo	2017-03-22 08:50:08.953	\N	2	50	2
208	f	Activo	2017-03-22 08:50:08.953	\N	3	50	2
209	f	Activo	2017-03-22 08:50:08.953	\N	4	50	2
210	f	Activo	2017-03-22 08:50:08.953	\N	6	50	2
211	f	Activo	2017-03-22 08:50:08.953	\N	1	51	2
212	f	Activo	2017-03-22 08:50:08.953	\N	2	51	2
213	f	Activo	2017-03-22 08:50:08.953	\N	3	51	2
214	f	Activo	2017-03-22 08:50:08.953	\N	4	51	2
215	f	Activo	2017-03-22 08:50:08.953	\N	6	51	2
216	f	Activo	2017-03-22 08:50:08.953	\N	1	52	2
217	f	Activo	2017-03-22 08:50:08.953	\N	2	52	2
218	f	Activo	2017-03-22 08:50:08.953	\N	3	52	2
219	f	Activo	2017-03-22 08:50:08.953	\N	4	52	2
220	f	Activo	2017-03-22 08:50:08.953	\N	6	52	2
221	f	Activo	2017-03-22 08:50:08.953	\N	1	53	2
222	f	Activo	2017-03-22 08:50:08.953	\N	2	53	2
223	f	Activo	2017-03-22 08:50:08.953	\N	3	53	2
224	f	Activo	2017-03-22 08:50:08.953	\N	4	53	2
225	f	Activo	2017-03-22 08:50:08.953	\N	6	53	2
226	f	Activo	2017-03-22 08:50:08.953	\N	1	55	2
227	f	Activo	2017-03-22 08:50:08.954	\N	2	55	2
228	f	Activo	2017-03-22 08:50:08.954	\N	3	55	2
229	f	Activo	2017-03-22 08:50:08.954	\N	4	55	2
230	f	Activo	2017-03-22 08:50:08.954	\N	6	55	2
231	f	Activo	2017-03-22 08:50:08.954	\N	1	56	2
232	f	Activo	2017-03-22 08:50:08.954	\N	2	56	2
233	f	Activo	2017-03-22 08:50:08.954	\N	3	56	2
234	f	Activo	2017-03-22 08:50:08.954	\N	4	56	2
235	f	Activo	2017-03-22 08:50:08.954	\N	6	56	2
236	f	Activo	2017-03-22 08:50:08.954	\N	1	59	2
237	f	Activo	2017-03-22 08:50:08.954	\N	2	59	2
238	f	Activo	2017-03-22 08:50:08.954	\N	3	59	2
239	f	Activo	2017-03-22 08:50:08.954	\N	4	59	2
240	f	Activo	2017-03-22 08:50:08.954	\N	6	59	2
241	f	Activo	2017-03-22 08:50:08.954	\N	1	60	2
242	f	Activo	2017-03-22 08:50:08.954	\N	2	60	2
243	f	Activo	2017-03-22 08:50:08.954	\N	3	60	2
244	f	Activo	2017-03-22 08:50:08.954	\N	4	60	2
245	f	Activo	2017-03-22 08:50:08.954	\N	6	60	2
246	f	Activo	2017-03-22 08:50:08.954	\N	1	62	2
247	f	Activo	2017-03-22 08:50:08.954	\N	2	62	2
248	f	Activo	2017-03-22 08:50:08.954	\N	3	62	2
249	f	Activo	2017-03-22 08:50:08.954	\N	4	62	2
250	f	Activo	2017-03-22 08:50:08.954	\N	6	62	2
251	f	Activo	2017-03-22 08:50:08.954	\N	1	63	2
252	f	Activo	2017-03-22 08:50:08.954	\N	2	63	2
253	f	Activo	2017-03-22 08:50:08.954	\N	3	63	2
254	f	Activo	2017-03-22 08:50:08.954	\N	4	63	2
255	f	Activo	2017-03-22 08:50:08.954	\N	6	63	2
256	f	Activo	2017-03-22 08:50:08.954	\N	1	66	2
257	f	Activo	2017-03-22 08:50:08.954	\N	2	66	2
258	f	Activo	2017-03-22 08:50:08.954	\N	3	66	2
259	f	Activo	2017-03-22 08:50:08.954	\N	4	66	2
260	f	Activo	2017-03-22 08:50:08.954	\N	6	66	2
261	f	Activo	2017-03-22 08:50:08.954	\N	1	68	2
262	f	Activo	2017-03-22 08:50:08.954	\N	2	68	2
263	f	Activo	2017-03-22 08:50:08.954	\N	3	68	2
264	f	Activo	2017-03-22 08:50:08.954	\N	4	68	2
265	f	Activo	2017-03-22 08:50:08.954	\N	6	68	2
266	f	Activo	2017-03-22 08:50:08.954	\N	1	69	2
267	f	Activo	2017-03-22 08:50:08.954	\N	2	69	2
268	f	Activo	2017-03-22 08:50:08.954	\N	3	69	2
269	f	Activo	2017-03-22 08:50:08.954	\N	4	69	2
270	f	Activo	2017-03-22 08:50:08.954	\N	6	69	2
271	f	Activo	2017-03-22 08:50:08.954	\N	1	71	2
272	f	Activo	2017-03-22 08:50:08.954	\N	2	71	2
273	f	Activo	2017-03-22 08:50:08.954	\N	3	71	2
274	f	Activo	2017-03-22 08:50:08.954	\N	4	71	2
275	f	Activo	2017-03-22 08:50:08.954	\N	6	71	2
276	f	Activo	2017-03-22 08:50:08.954	\N	1	72	2
277	f	Activo	2017-03-22 08:50:08.954	\N	2	72	2
278	f	Activo	2017-03-22 08:50:08.954	\N	3	72	2
279	f	Activo	2017-03-22 08:50:08.954	\N	4	72	2
280	f	Activo	2017-03-22 08:50:08.954	\N	6	72	2
281	f	Activo	2017-03-22 08:50:08.954	\N	1	73	2
282	f	Activo	2017-03-22 08:50:08.954	\N	2	73	2
283	f	Activo	2017-03-22 08:50:08.954	\N	3	73	2
284	f	Activo	2017-03-22 08:50:08.954	\N	4	73	2
285	f	Activo	2017-03-22 08:50:08.954	\N	6	73	2
286	f	Activo	2017-03-22 08:50:08.954	\N	1	74	2
287	f	Activo	2017-03-22 08:50:08.954	\N	2	74	2
288	f	Activo	2017-03-22 08:50:08.954	\N	3	74	2
289	f	Activo	2017-03-22 08:50:08.954	\N	4	74	2
290	f	Activo	2017-03-22 08:50:08.954	\N	6	74	2
291	f	Activo	2017-03-22 08:50:08.954	\N	1	75	2
292	f	Activo	2017-03-22 08:50:08.954	\N	2	75	2
293	f	Activo	2017-03-22 08:50:08.954	\N	3	75	2
294	f	Activo	2017-03-22 08:50:08.954	\N	4	75	2
295	f	Activo	2017-03-22 08:50:08.954	\N	6	75	2
296	f	Activo	2017-03-22 08:50:08.954	\N	1	76	2
297	f	Activo	2017-03-22 08:50:08.954	\N	2	76	2
298	f	Activo	2017-03-22 08:50:08.954	\N	3	76	2
299	f	Activo	2017-03-22 08:50:08.954	\N	4	76	2
300	f	Activo	2017-03-22 08:50:08.954	\N	6	76	2
301	f	Activo	2017-03-22 08:50:08.954	\N	1	77	2
302	f	Activo	2017-03-22 08:50:08.954	\N	2	77	2
303	f	Activo	2017-03-22 08:50:08.954	\N	3	77	2
304	f	Activo	2017-03-22 08:50:08.954	\N	4	77	2
305	f	Activo	2017-03-22 08:50:08.954	\N	6	77	2
306	f	Activo	2017-03-22 08:50:08.954	\N	1	79	2
307	f	Activo	2017-03-22 08:50:08.954	\N	2	79	2
308	f	Activo	2017-03-22 08:50:08.954	\N	3	79	2
309	f	Activo	2017-03-22 08:50:08.954	\N	4	79	2
310	f	Activo	2017-03-22 08:50:08.954	\N	6	79	2
311	f	Activo	2017-03-22 08:50:08.954	\N	1	80	2
312	f	Activo	2017-03-22 08:50:08.954	\N	2	80	2
313	f	Activo	2017-03-22 08:50:08.954	\N	3	80	2
314	f	Activo	2017-03-22 08:50:08.954	\N	4	80	2
315	f	Activo	2017-03-22 08:50:08.954	\N	6	80	2
316	f	Activo	2017-03-22 08:50:08.954	\N	1	82	2
317	f	Activo	2017-03-22 08:50:08.954	\N	2	82	2
318	f	Activo	2017-03-22 08:50:08.954	\N	3	82	2
319	f	Activo	2017-03-22 08:50:08.954	\N	4	82	2
320	f	Activo	2017-03-22 08:50:08.954	\N	6	82	2
321	f	Activo	2017-03-22 08:50:08.954	\N	1	85	2
322	f	Activo	2017-03-22 08:50:08.954	\N	2	85	2
323	f	Activo	2017-03-22 08:50:08.954	\N	3	85	2
324	f	Activo	2017-03-22 08:50:08.954	\N	4	85	2
325	f	Activo	2017-03-22 08:50:08.954	\N	6	85	2
326	f	Activo	2017-03-22 08:50:08.954	\N	1	86	2
327	f	Activo	2017-03-22 08:50:08.954	\N	2	86	2
328	f	Activo	2017-03-22 08:50:08.954	\N	3	86	2
329	f	Activo	2017-03-22 08:50:08.954	\N	4	86	2
330	f	Activo	2017-03-22 08:50:08.954	\N	6	86	2
331	f	Activo	2017-03-22 08:50:08.954	\N	1	87	2
332	f	Activo	2017-03-22 08:50:08.954	\N	2	87	2
333	f	Activo	2017-03-22 08:50:08.954	\N	3	87	2
334	f	Activo	2017-03-22 08:50:08.954	\N	4	87	2
335	f	Activo	2017-03-22 08:50:08.954	\N	6	87	2
336	f	Activo	2017-03-22 08:50:08.954	\N	1	88	2
337	f	Activo	2017-03-22 08:50:08.954	\N	2	88	2
338	f	Activo	2017-03-22 08:50:08.954	\N	3	88	2
339	f	Activo	2017-03-22 08:50:08.954	\N	4	88	2
340	f	Activo	2017-03-22 08:50:08.954	\N	6	88	2
341	f	Activo	2017-03-22 08:50:08.954	\N	1	89	2
342	f	Activo	2017-03-22 08:50:08.954	\N	2	89	2
343	f	Activo	2017-03-22 08:50:08.954	\N	3	89	2
344	f	Activo	2017-03-22 08:50:08.954	\N	4	89	2
345	f	Activo	2017-03-22 08:50:08.954	\N	6	89	2
346	f	Activo	2017-03-22 08:50:08.954	\N	1	90	2
347	f	Activo	2017-03-22 08:50:08.954	\N	2	90	2
348	f	Activo	2017-03-22 08:50:08.954	\N	3	90	2
349	f	Activo	2017-03-22 08:50:08.954	\N	4	90	2
350	f	Activo	2017-03-22 08:50:08.954	\N	6	90	2
351	f	Activo	2017-03-22 08:50:08.954	\N	1	91	2
352	f	Activo	2017-03-22 08:50:08.954	\N	2	91	2
353	f	Activo	2017-03-22 08:50:08.954	\N	3	91	2
354	f	Activo	2017-03-22 08:50:08.954	\N	4	91	2
355	f	Activo	2017-03-22 08:50:08.954	\N	6	91	2
356	f	Activo	2017-03-22 08:50:08.954	\N	1	92	2
357	f	Activo	2017-03-22 08:50:08.954	\N	2	92	2
358	f	Activo	2017-03-22 08:50:08.954	\N	3	92	2
359	f	Activo	2017-03-22 08:50:08.954	\N	4	92	2
360	f	Activo	2017-03-22 08:50:08.954	\N	6	92	2
361	f	Activo	2017-03-22 08:50:08.954	\N	1	94	2
362	f	Activo	2017-03-22 08:50:08.954	\N	2	94	2
363	f	Activo	2017-03-22 08:50:08.954	\N	3	94	2
364	f	Activo	2017-03-22 08:50:08.954	\N	4	94	2
365	f	Activo	2017-03-22 08:50:08.954	\N	6	94	2
366	f	Activo	2017-03-22 08:50:08.955	\N	1	96	2
367	f	Activo	2017-03-22 08:50:08.955	\N	2	96	2
368	f	Activo	2017-03-22 08:50:08.955	\N	3	96	2
369	f	Activo	2017-03-22 08:50:08.955	\N	4	96	2
370	f	Activo	2017-03-22 08:50:08.955	\N	6	96	2
371	f	Activo	2017-03-22 08:50:08.955	\N	1	97	2
372	f	Activo	2017-03-22 08:50:08.955	\N	2	97	2
373	f	Activo	2017-03-22 08:50:08.955	\N	3	97	2
374	f	Activo	2017-03-22 08:50:08.955	\N	4	97	2
375	f	Activo	2017-03-22 08:50:08.955	\N	6	97	2
376	f	Activo	2017-03-22 08:50:08.955	\N	1	99	2
377	f	Activo	2017-03-22 08:50:08.955	\N	2	99	2
378	f	Activo	2017-03-22 08:50:08.955	\N	3	99	2
379	f	Activo	2017-03-22 08:50:08.955	\N	4	99	2
380	f	Activo	2017-03-22 08:50:08.955	\N	6	99	2
381	f	Activo	2017-03-22 08:50:08.955	\N	1	100	2
382	f	Activo	2017-03-22 08:50:08.955	\N	2	100	2
383	f	Activo	2017-03-22 08:50:08.955	\N	3	100	2
384	f	Activo	2017-03-22 08:50:08.955	\N	4	100	2
385	f	Activo	2017-03-22 08:50:08.955	\N	6	100	2
386	f	Activo	2017-03-22 08:50:08.955	\N	1	101	2
387	f	Activo	2017-03-22 08:50:08.955	\N	2	101	2
388	f	Activo	2017-03-22 08:50:08.955	\N	3	101	2
389	f	Activo	2017-03-22 08:50:08.955	\N	4	101	2
390	f	Activo	2017-03-22 08:50:08.955	\N	6	101	2
391	f	Activo	2017-03-22 08:50:09.121	\N	5	4	2
392	f	Activo	2017-03-22 08:50:09.121	\N	5	3	2
393	f	Activo	2017-03-22 08:50:09.121	\N	5	2	2
394	f	Activo	2017-03-22 08:50:09.121	\N	5	14	2
395	f	Activo	2017-03-22 08:50:09.121	\N	5	38	2
396	f	Activo	2017-03-22 08:50:09.121	\N	5	40	2
397	f	Activo	2017-03-22 08:50:09.121	\N	5	44	2
398	f	Activo	2017-03-22 08:50:09.121	\N	5	48	2
399	f	Activo	2017-03-22 08:50:09.121	\N	5	54	2
400	f	Activo	2017-03-22 08:50:09.121	\N	5	58	2
401	f	Activo	2017-03-22 08:50:09.121	\N	5	57	2
402	f	Activo	2017-03-22 08:50:09.121	\N	5	61	2
403	f	Activo	2017-03-22 08:50:09.121	\N	5	65	2
404	f	Activo	2017-03-22 08:50:09.121	\N	5	64	2
405	f	Activo	2017-03-22 08:50:09.121	\N	5	67	2
406	f	Activo	2017-03-22 08:50:09.121	\N	5	70	2
407	f	Activo	2017-03-22 08:50:09.121	\N	5	78	2
408	f	Activo	2017-03-22 08:50:09.121	\N	5	81	2
409	f	Activo	2017-03-22 08:50:09.121	\N	5	84	2
410	f	Activo	2017-03-22 08:50:09.121	\N	5	83	2
411	f	Activo	2017-03-22 08:50:09.121	\N	5	93	2
412	f	Activo	2017-03-22 08:50:09.121	\N	5	95	2
413	f	Activo	2017-03-22 08:50:09.121	\N	5	98	2
414	f	Activo	2017-03-22 08:50:14.031	\N	1	1	3
415	f	Activo	2017-03-22 08:50:14.031	\N	2	1	3
416	f	Activo	2017-03-22 08:50:14.031	\N	3	1	3
417	f	Activo	2017-03-22 08:50:14.031	\N	4	1	3
418	f	Activo	2017-03-22 08:50:14.031	\N	6	1	3
419	f	Activo	2017-03-22 08:50:14.031	\N	1	5	3
420	f	Activo	2017-03-22 08:50:14.031	\N	2	5	3
421	f	Activo	2017-03-22 08:50:14.031	\N	3	5	3
422	f	Activo	2017-03-22 08:50:14.031	\N	4	5	3
423	f	Activo	2017-03-22 08:50:14.031	\N	6	5	3
424	f	Activo	2017-03-22 08:50:14.031	\N	1	6	3
425	f	Activo	2017-03-22 08:50:14.031	\N	2	6	3
426	f	Activo	2017-03-22 08:50:14.031	\N	3	6	3
427	f	Activo	2017-03-22 08:50:14.031	\N	4	6	3
428	f	Activo	2017-03-22 08:50:14.031	\N	6	6	3
429	f	Activo	2017-03-22 08:50:14.031	\N	1	7	3
430	f	Activo	2017-03-22 08:50:14.031	\N	2	7	3
431	f	Activo	2017-03-22 08:50:14.031	\N	3	7	3
432	f	Activo	2017-03-22 08:50:14.031	\N	4	7	3
433	f	Activo	2017-03-22 08:50:14.031	\N	6	7	3
434	f	Activo	2017-03-22 08:50:14.031	\N	1	8	3
435	f	Activo	2017-03-22 08:50:14.031	\N	2	8	3
436	f	Activo	2017-03-22 08:50:14.031	\N	3	8	3
437	f	Activo	2017-03-22 08:50:14.031	\N	4	8	3
438	f	Activo	2017-03-22 08:50:14.031	\N	6	8	3
439	f	Activo	2017-03-22 08:50:14.031	\N	1	9	3
440	f	Activo	2017-03-22 08:50:14.031	\N	2	9	3
441	f	Activo	2017-03-22 08:50:14.031	\N	3	9	3
442	f	Activo	2017-03-22 08:50:14.031	\N	4	9	3
443	f	Activo	2017-03-22 08:50:14.031	\N	6	9	3
444	f	Activo	2017-03-22 08:50:14.031	\N	1	10	3
445	f	Activo	2017-03-22 08:50:14.031	\N	2	10	3
446	f	Activo	2017-03-22 08:50:14.031	\N	3	10	3
447	f	Activo	2017-03-22 08:50:14.031	\N	4	10	3
448	f	Activo	2017-03-22 08:50:14.031	\N	6	10	3
449	f	Activo	2017-03-22 08:50:14.031	\N	1	11	3
450	f	Activo	2017-03-22 08:50:14.031	\N	2	11	3
451	f	Activo	2017-03-22 08:50:14.031	\N	3	11	3
452	f	Activo	2017-03-22 08:50:14.031	\N	4	11	3
453	f	Activo	2017-03-22 08:50:14.031	\N	6	11	3
454	f	Activo	2017-03-22 08:50:14.031	\N	1	12	3
455	f	Activo	2017-03-22 08:50:14.031	\N	2	12	3
456	f	Activo	2017-03-22 08:50:14.031	\N	3	12	3
457	f	Activo	2017-03-22 08:50:14.031	\N	4	12	3
458	f	Activo	2017-03-22 08:50:14.031	\N	6	12	3
459	f	Activo	2017-03-22 08:50:14.031	\N	1	13	3
460	f	Activo	2017-03-22 08:50:14.031	\N	2	13	3
461	f	Activo	2017-03-22 08:50:14.031	\N	3	13	3
462	f	Activo	2017-03-22 08:50:14.031	\N	4	13	3
463	f	Activo	2017-03-22 08:50:14.031	\N	6	13	3
464	f	Activo	2017-03-22 08:50:14.031	\N	1	15	3
465	f	Activo	2017-03-22 08:50:14.031	\N	2	15	3
466	f	Activo	2017-03-22 08:50:14.031	\N	3	15	3
467	f	Activo	2017-03-22 08:50:14.031	\N	4	15	3
468	f	Activo	2017-03-22 08:50:14.031	\N	6	15	3
469	f	Activo	2017-03-22 08:50:14.031	\N	1	16	3
470	f	Activo	2017-03-22 08:50:14.031	\N	2	16	3
471	f	Activo	2017-03-22 08:50:14.031	\N	3	16	3
472	f	Activo	2017-03-22 08:50:14.031	\N	4	16	3
473	f	Activo	2017-03-22 08:50:14.031	\N	6	16	3
474	f	Activo	2017-03-22 08:50:14.031	\N	1	17	3
475	f	Activo	2017-03-22 08:50:14.031	\N	2	17	3
476	f	Activo	2017-03-22 08:50:14.031	\N	3	17	3
477	f	Activo	2017-03-22 08:50:14.031	\N	4	17	3
478	f	Activo	2017-03-22 08:50:14.031	\N	6	17	3
479	f	Activo	2017-03-22 08:50:14.031	\N	1	18	3
480	f	Activo	2017-03-22 08:50:14.031	\N	2	18	3
481	f	Activo	2017-03-22 08:50:14.031	\N	3	18	3
482	f	Activo	2017-03-22 08:50:14.031	\N	4	18	3
483	f	Activo	2017-03-22 08:50:14.031	\N	6	18	3
484	f	Activo	2017-03-22 08:50:14.031	\N	1	19	3
485	f	Activo	2017-03-22 08:50:14.031	\N	2	19	3
486	f	Activo	2017-03-22 08:50:14.031	\N	3	19	3
487	f	Activo	2017-03-22 08:50:14.031	\N	4	19	3
488	f	Activo	2017-03-22 08:50:14.031	\N	6	19	3
489	f	Activo	2017-03-22 08:50:14.031	\N	1	20	3
490	f	Activo	2017-03-22 08:50:14.031	\N	2	20	3
491	f	Activo	2017-03-22 08:50:14.031	\N	3	20	3
492	f	Activo	2017-03-22 08:50:14.031	\N	4	20	3
493	f	Activo	2017-03-22 08:50:14.031	\N	6	20	3
494	f	Activo	2017-03-22 08:50:14.031	\N	1	21	3
495	f	Activo	2017-03-22 08:50:14.031	\N	2	21	3
496	f	Activo	2017-03-22 08:50:14.031	\N	3	21	3
497	f	Activo	2017-03-22 08:50:14.031	\N	4	21	3
498	f	Activo	2017-03-22 08:50:14.031	\N	6	21	3
499	f	Activo	2017-03-22 08:50:14.031	\N	1	22	3
500	f	Activo	2017-03-22 08:50:14.031	\N	2	22	3
501	f	Activo	2017-03-22 08:50:14.031	\N	3	22	3
502	f	Activo	2017-03-22 08:50:14.031	\N	4	22	3
503	f	Activo	2017-03-22 08:50:14.031	\N	6	22	3
504	f	Activo	2017-03-22 08:50:14.031	\N	1	23	3
505	f	Activo	2017-03-22 08:50:14.031	\N	2	23	3
506	f	Activo	2017-03-22 08:50:14.031	\N	3	23	3
507	f	Activo	2017-03-22 08:50:14.031	\N	4	23	3
508	f	Activo	2017-03-22 08:50:14.031	\N	6	23	3
509	f	Activo	2017-03-22 08:50:14.031	\N	1	24	3
510	f	Activo	2017-03-22 08:50:14.031	\N	2	24	3
511	f	Activo	2017-03-22 08:50:14.031	\N	3	24	3
512	f	Activo	2017-03-22 08:50:14.031	\N	4	24	3
513	f	Activo	2017-03-22 08:50:14.031	\N	6	24	3
514	f	Activo	2017-03-22 08:50:14.031	\N	1	25	3
515	f	Activo	2017-03-22 08:50:14.031	\N	2	25	3
516	f	Activo	2017-03-22 08:50:14.031	\N	3	25	3
517	f	Activo	2017-03-22 08:50:14.031	\N	4	25	3
518	f	Activo	2017-03-22 08:50:14.031	\N	6	25	3
519	f	Activo	2017-03-22 08:50:14.031	\N	1	26	3
520	f	Activo	2017-03-22 08:50:14.031	\N	2	26	3
521	f	Activo	2017-03-22 08:50:14.031	\N	3	26	3
522	f	Activo	2017-03-22 08:50:14.031	\N	4	26	3
523	f	Activo	2017-03-22 08:50:14.031	\N	6	26	3
524	f	Activo	2017-03-22 08:50:14.031	\N	1	27	3
525	f	Activo	2017-03-22 08:50:14.031	\N	2	27	3
526	f	Activo	2017-03-22 08:50:14.031	\N	3	27	3
527	f	Activo	2017-03-22 08:50:14.031	\N	4	27	3
528	f	Activo	2017-03-22 08:50:14.031	\N	6	27	3
529	f	Activo	2017-03-22 08:50:14.031	\N	1	28	3
530	f	Activo	2017-03-22 08:50:14.031	\N	2	28	3
531	f	Activo	2017-03-22 08:50:14.031	\N	3	28	3
532	f	Activo	2017-03-22 08:50:14.031	\N	4	28	3
533	f	Activo	2017-03-22 08:50:14.031	\N	6	28	3
534	f	Activo	2017-03-22 08:50:14.031	\N	1	29	3
535	f	Activo	2017-03-22 08:50:14.031	\N	2	29	3
536	f	Activo	2017-03-22 08:50:14.031	\N	3	29	3
537	f	Activo	2017-03-22 08:50:14.031	\N	4	29	3
538	f	Activo	2017-03-22 08:50:14.031	\N	6	29	3
539	f	Activo	2017-03-22 08:50:14.031	\N	1	30	3
540	f	Activo	2017-03-22 08:50:14.031	\N	2	30	3
541	f	Activo	2017-03-22 08:50:14.031	\N	3	30	3
542	f	Activo	2017-03-22 08:50:14.031	\N	4	30	3
543	f	Activo	2017-03-22 08:50:14.031	\N	6	30	3
544	f	Activo	2017-03-22 08:50:14.031	\N	1	31	3
545	f	Activo	2017-03-22 08:50:14.031	\N	2	31	3
546	f	Activo	2017-03-22 08:50:14.031	\N	3	31	3
547	f	Activo	2017-03-22 08:50:14.031	\N	4	31	3
548	f	Activo	2017-03-22 08:50:14.031	\N	6	31	3
549	f	Activo	2017-03-22 08:50:14.031	\N	1	32	3
550	f	Activo	2017-03-22 08:50:14.031	\N	2	32	3
551	f	Activo	2017-03-22 08:50:14.031	\N	3	32	3
552	f	Activo	2017-03-22 08:50:14.031	\N	4	32	3
553	f	Activo	2017-03-22 08:50:14.031	\N	6	32	3
554	f	Activo	2017-03-22 08:50:14.031	\N	1	33	3
555	f	Activo	2017-03-22 08:50:14.031	\N	2	33	3
556	f	Activo	2017-03-22 08:50:14.031	\N	3	33	3
557	f	Activo	2017-03-22 08:50:14.031	\N	4	33	3
558	f	Activo	2017-03-22 08:50:14.031	\N	6	33	3
559	f	Activo	2017-03-22 08:50:14.031	\N	1	34	3
560	f	Activo	2017-03-22 08:50:14.031	\N	2	34	3
561	f	Activo	2017-03-22 08:50:14.031	\N	3	34	3
562	f	Activo	2017-03-22 08:50:14.031	\N	4	34	3
563	f	Activo	2017-03-22 08:50:14.031	\N	6	34	3
564	f	Activo	2017-03-22 08:50:14.031	\N	1	35	3
565	f	Activo	2017-03-22 08:50:14.031	\N	2	35	3
566	f	Activo	2017-03-22 08:50:14.031	\N	3	35	3
567	f	Activo	2017-03-22 08:50:14.031	\N	4	35	3
568	f	Activo	2017-03-22 08:50:14.031	\N	6	35	3
569	f	Activo	2017-03-22 08:50:14.031	\N	1	36	3
570	f	Activo	2017-03-22 08:50:14.031	\N	2	36	3
571	f	Activo	2017-03-22 08:50:14.031	\N	3	36	3
572	f	Activo	2017-03-22 08:50:14.031	\N	4	36	3
573	f	Activo	2017-03-22 08:50:14.031	\N	6	36	3
574	f	Activo	2017-03-22 08:50:14.031	\N	1	37	3
575	f	Activo	2017-03-22 08:50:14.031	\N	2	37	3
576	f	Activo	2017-03-22 08:50:14.031	\N	3	37	3
577	f	Activo	2017-03-22 08:50:14.031	\N	4	37	3
578	f	Activo	2017-03-22 08:50:14.031	\N	6	37	3
579	f	Activo	2017-03-22 08:50:14.031	\N	1	39	3
580	f	Activo	2017-03-22 08:50:14.031	\N	2	39	3
581	f	Activo	2017-03-22 08:50:14.031	\N	3	39	3
582	f	Activo	2017-03-22 08:50:14.031	\N	4	39	3
583	f	Activo	2017-03-22 08:50:14.031	\N	6	39	3
584	f	Activo	2017-03-22 08:50:14.031	\N	1	41	3
585	f	Activo	2017-03-22 08:50:14.031	\N	2	41	3
586	f	Activo	2017-03-22 08:50:14.031	\N	3	41	3
587	f	Activo	2017-03-22 08:50:14.031	\N	4	41	3
588	f	Activo	2017-03-22 08:50:14.031	\N	6	41	3
589	f	Activo	2017-03-22 08:50:14.031	\N	1	42	3
590	f	Activo	2017-03-22 08:50:14.031	\N	2	42	3
591	f	Activo	2017-03-22 08:50:14.031	\N	3	42	3
592	f	Activo	2017-03-22 08:50:14.031	\N	4	42	3
593	f	Activo	2017-03-22 08:50:14.031	\N	6	42	3
594	f	Activo	2017-03-22 08:50:14.031	\N	1	43	3
595	f	Activo	2017-03-22 08:50:14.031	\N	2	43	3
596	f	Activo	2017-03-22 08:50:14.031	\N	3	43	3
597	f	Activo	2017-03-22 08:50:14.031	\N	4	43	3
598	f	Activo	2017-03-22 08:50:14.031	\N	6	43	3
599	f	Activo	2017-03-22 08:50:14.031	\N	1	45	3
600	f	Activo	2017-03-22 08:50:14.031	\N	2	45	3
601	f	Activo	2017-03-22 08:50:14.031	\N	3	45	3
602	f	Activo	2017-03-22 08:50:14.031	\N	4	45	3
603	f	Activo	2017-03-22 08:50:14.031	\N	6	45	3
604	f	Activo	2017-03-22 08:50:14.031	\N	1	46	3
605	f	Activo	2017-03-22 08:50:14.031	\N	2	46	3
606	f	Activo	2017-03-22 08:50:14.031	\N	3	46	3
607	f	Activo	2017-03-22 08:50:14.031	\N	4	46	3
608	f	Activo	2017-03-22 08:50:14.031	\N	6	46	3
609	f	Activo	2017-03-22 08:50:14.031	\N	1	47	3
610	f	Activo	2017-03-22 08:50:14.031	\N	2	47	3
611	f	Activo	2017-03-22 08:50:14.031	\N	3	47	3
612	f	Activo	2017-03-22 08:50:14.031	\N	4	47	3
613	f	Activo	2017-03-22 08:50:14.031	\N	6	47	3
614	f	Activo	2017-03-22 08:50:14.031	\N	1	49	3
615	f	Activo	2017-03-22 08:50:14.031	\N	2	49	3
616	f	Activo	2017-03-22 08:50:14.031	\N	3	49	3
617	f	Activo	2017-03-22 08:50:14.031	\N	4	49	3
618	f	Activo	2017-03-22 08:50:14.031	\N	6	49	3
619	f	Activo	2017-03-22 08:50:14.031	\N	1	50	3
620	f	Activo	2017-03-22 08:50:14.031	\N	2	50	3
621	f	Activo	2017-03-22 08:50:14.031	\N	3	50	3
622	f	Activo	2017-03-22 08:50:14.031	\N	4	50	3
623	f	Activo	2017-03-22 08:50:14.031	\N	6	50	3
624	f	Activo	2017-03-22 08:50:14.031	\N	1	51	3
625	f	Activo	2017-03-22 08:50:14.031	\N	2	51	3
626	f	Activo	2017-03-22 08:50:14.031	\N	3	51	3
627	f	Activo	2017-03-22 08:50:14.031	\N	4	51	3
628	f	Activo	2017-03-22 08:50:14.031	\N	6	51	3
629	f	Activo	2017-03-22 08:50:14.031	\N	1	52	3
630	f	Activo	2017-03-22 08:50:14.031	\N	2	52	3
631	f	Activo	2017-03-22 08:50:14.031	\N	3	52	3
632	f	Activo	2017-03-22 08:50:14.031	\N	4	52	3
633	f	Activo	2017-03-22 08:50:14.032	\N	6	52	3
634	f	Activo	2017-03-22 08:50:14.032	\N	1	53	3
635	f	Activo	2017-03-22 08:50:14.032	\N	2	53	3
636	f	Activo	2017-03-22 08:50:14.032	\N	3	53	3
637	f	Activo	2017-03-22 08:50:14.032	\N	4	53	3
638	f	Activo	2017-03-22 08:50:14.032	\N	6	53	3
639	f	Activo	2017-03-22 08:50:14.032	\N	1	55	3
640	f	Activo	2017-03-22 08:50:14.032	\N	2	55	3
641	f	Activo	2017-03-22 08:50:14.032	\N	3	55	3
642	f	Activo	2017-03-22 08:50:14.032	\N	4	55	3
643	f	Activo	2017-03-22 08:50:14.032	\N	6	55	3
644	f	Activo	2017-03-22 08:50:14.032	\N	1	56	3
645	f	Activo	2017-03-22 08:50:14.032	\N	2	56	3
646	f	Activo	2017-03-22 08:50:14.032	\N	3	56	3
647	f	Activo	2017-03-22 08:50:14.032	\N	4	56	3
648	f	Activo	2017-03-22 08:50:14.032	\N	6	56	3
649	f	Activo	2017-03-22 08:50:14.032	\N	1	59	3
650	f	Activo	2017-03-22 08:50:14.032	\N	2	59	3
651	f	Activo	2017-03-22 08:50:14.032	\N	3	59	3
652	f	Activo	2017-03-22 08:50:14.032	\N	4	59	3
653	f	Activo	2017-03-22 08:50:14.032	\N	6	59	3
654	f	Activo	2017-03-22 08:50:14.032	\N	1	60	3
655	f	Activo	2017-03-22 08:50:14.032	\N	2	60	3
656	f	Activo	2017-03-22 08:50:14.032	\N	3	60	3
657	f	Activo	2017-03-22 08:50:14.032	\N	4	60	3
658	f	Activo	2017-03-22 08:50:14.032	\N	6	60	3
659	f	Activo	2017-03-22 08:50:14.032	\N	1	62	3
660	f	Activo	2017-03-22 08:50:14.032	\N	2	62	3
661	f	Activo	2017-03-22 08:50:14.032	\N	3	62	3
662	f	Activo	2017-03-22 08:50:14.032	\N	4	62	3
663	f	Activo	2017-03-22 08:50:14.032	\N	6	62	3
664	f	Activo	2017-03-22 08:50:14.032	\N	1	63	3
665	f	Activo	2017-03-22 08:50:14.032	\N	2	63	3
666	f	Activo	2017-03-22 08:50:14.032	\N	3	63	3
667	f	Activo	2017-03-22 08:50:14.032	\N	4	63	3
668	f	Activo	2017-03-22 08:50:14.032	\N	6	63	3
669	f	Activo	2017-03-22 08:50:14.032	\N	1	66	3
670	f	Activo	2017-03-22 08:50:14.032	\N	2	66	3
671	f	Activo	2017-03-22 08:50:14.032	\N	3	66	3
672	f	Activo	2017-03-22 08:50:14.032	\N	4	66	3
673	f	Activo	2017-03-22 08:50:14.032	\N	6	66	3
674	f	Activo	2017-03-22 08:50:14.032	\N	1	68	3
675	f	Activo	2017-03-22 08:50:14.032	\N	2	68	3
676	f	Activo	2017-03-22 08:50:14.032	\N	3	68	3
677	f	Activo	2017-03-22 08:50:14.032	\N	4	68	3
678	f	Activo	2017-03-22 08:50:14.032	\N	6	68	3
679	f	Activo	2017-03-22 08:50:14.032	\N	1	69	3
680	f	Activo	2017-03-22 08:50:14.032	\N	2	69	3
681	f	Activo	2017-03-22 08:50:14.032	\N	3	69	3
682	f	Activo	2017-03-22 08:50:14.032	\N	4	69	3
683	f	Activo	2017-03-22 08:50:14.032	\N	6	69	3
684	f	Activo	2017-03-22 08:50:14.032	\N	1	71	3
685	f	Activo	2017-03-22 08:50:14.032	\N	2	71	3
686	f	Activo	2017-03-22 08:50:14.032	\N	3	71	3
687	f	Activo	2017-03-22 08:50:14.032	\N	4	71	3
688	f	Activo	2017-03-22 08:50:14.032	\N	6	71	3
689	f	Activo	2017-03-22 08:50:14.032	\N	1	72	3
690	f	Activo	2017-03-22 08:50:14.032	\N	2	72	3
691	f	Activo	2017-03-22 08:50:14.032	\N	3	72	3
692	f	Activo	2017-03-22 08:50:14.032	\N	4	72	3
693	f	Activo	2017-03-22 08:50:14.032	\N	6	72	3
694	f	Activo	2017-03-22 08:50:14.032	\N	1	73	3
695	f	Activo	2017-03-22 08:50:14.032	\N	2	73	3
696	f	Activo	2017-03-22 08:50:14.032	\N	3	73	3
697	f	Activo	2017-03-22 08:50:14.032	\N	4	73	3
698	f	Activo	2017-03-22 08:50:14.032	\N	6	73	3
699	f	Activo	2017-03-22 08:50:14.032	\N	1	74	3
700	f	Activo	2017-03-22 08:50:14.032	\N	2	74	3
701	f	Activo	2017-03-22 08:50:14.032	\N	3	74	3
702	f	Activo	2017-03-22 08:50:14.032	\N	4	74	3
703	f	Activo	2017-03-22 08:50:14.032	\N	6	74	3
704	f	Activo	2017-03-22 08:50:14.032	\N	1	75	3
705	f	Activo	2017-03-22 08:50:14.032	\N	2	75	3
706	f	Activo	2017-03-22 08:50:14.032	\N	3	75	3
707	f	Activo	2017-03-22 08:50:14.032	\N	4	75	3
708	f	Activo	2017-03-22 08:50:14.032	\N	6	75	3
709	f	Activo	2017-03-22 08:50:14.032	\N	1	76	3
710	f	Activo	2017-03-22 08:50:14.032	\N	2	76	3
711	f	Activo	2017-03-22 08:50:14.032	\N	3	76	3
712	f	Activo	2017-03-22 08:50:14.032	\N	4	76	3
713	f	Activo	2017-03-22 08:50:14.032	\N	6	76	3
714	f	Activo	2017-03-22 08:50:14.032	\N	1	77	3
715	f	Activo	2017-03-22 08:50:14.032	\N	2	77	3
716	f	Activo	2017-03-22 08:50:14.032	\N	3	77	3
717	f	Activo	2017-03-22 08:50:14.032	\N	4	77	3
718	f	Activo	2017-03-22 08:50:14.032	\N	6	77	3
719	f	Activo	2017-03-22 08:50:14.032	\N	1	79	3
720	f	Activo	2017-03-22 08:50:14.032	\N	2	79	3
721	f	Activo	2017-03-22 08:50:14.032	\N	3	79	3
722	f	Activo	2017-03-22 08:50:14.032	\N	4	79	3
723	f	Activo	2017-03-22 08:50:14.032	\N	6	79	3
724	f	Activo	2017-03-22 08:50:14.032	\N	1	80	3
725	f	Activo	2017-03-22 08:50:14.032	\N	2	80	3
726	f	Activo	2017-03-22 08:50:14.032	\N	3	80	3
727	f	Activo	2017-03-22 08:50:14.032	\N	4	80	3
728	f	Activo	2017-03-22 08:50:14.032	\N	6	80	3
729	f	Activo	2017-03-22 08:50:14.032	\N	1	82	3
730	f	Activo	2017-03-22 08:50:14.032	\N	2	82	3
731	f	Activo	2017-03-22 08:50:14.032	\N	3	82	3
732	f	Activo	2017-03-22 08:50:14.032	\N	4	82	3
733	f	Activo	2017-03-22 08:50:14.032	\N	6	82	3
734	f	Activo	2017-03-22 08:50:14.032	\N	1	85	3
735	f	Activo	2017-03-22 08:50:14.032	\N	2	85	3
736	f	Activo	2017-03-22 08:50:14.032	\N	3	85	3
737	f	Activo	2017-03-22 08:50:14.032	\N	4	85	3
738	f	Activo	2017-03-22 08:50:14.032	\N	6	85	3
739	f	Activo	2017-03-22 08:50:14.032	\N	1	86	3
740	f	Activo	2017-03-22 08:50:14.032	\N	2	86	3
741	f	Activo	2017-03-22 08:50:14.032	\N	3	86	3
742	f	Activo	2017-03-22 08:50:14.032	\N	4	86	3
743	f	Activo	2017-03-22 08:50:14.032	\N	6	86	3
744	f	Activo	2017-03-22 08:50:14.032	\N	1	87	3
745	f	Activo	2017-03-22 08:50:14.032	\N	2	87	3
746	f	Activo	2017-03-22 08:50:14.032	\N	3	87	3
747	f	Activo	2017-03-22 08:50:14.032	\N	4	87	3
748	f	Activo	2017-03-22 08:50:14.032	\N	6	87	3
749	f	Activo	2017-03-22 08:50:14.032	\N	1	88	3
750	f	Activo	2017-03-22 08:50:14.032	\N	2	88	3
751	f	Activo	2017-03-22 08:50:14.032	\N	3	88	3
752	f	Activo	2017-03-22 08:50:14.032	\N	4	88	3
753	f	Activo	2017-03-22 08:50:14.032	\N	6	88	3
754	f	Activo	2017-03-22 08:50:14.032	\N	1	89	3
755	f	Activo	2017-03-22 08:50:14.032	\N	2	89	3
756	f	Activo	2017-03-22 08:50:14.032	\N	3	89	3
757	f	Activo	2017-03-22 08:50:14.032	\N	4	89	3
758	f	Activo	2017-03-22 08:50:14.032	\N	6	89	3
759	f	Activo	2017-03-22 08:50:14.032	\N	1	90	3
760	f	Activo	2017-03-22 08:50:14.032	\N	2	90	3
761	f	Activo	2017-03-22 08:50:14.032	\N	3	90	3
762	f	Activo	2017-03-22 08:50:14.032	\N	4	90	3
763	f	Activo	2017-03-22 08:50:14.032	\N	6	90	3
764	f	Activo	2017-03-22 08:50:14.032	\N	1	91	3
765	f	Activo	2017-03-22 08:50:14.032	\N	2	91	3
766	f	Activo	2017-03-22 08:50:14.032	\N	3	91	3
767	f	Activo	2017-03-22 08:50:14.032	\N	4	91	3
768	f	Activo	2017-03-22 08:50:14.032	\N	6	91	3
769	f	Activo	2017-03-22 08:50:14.032	\N	1	92	3
770	f	Activo	2017-03-22 08:50:14.032	\N	2	92	3
771	f	Activo	2017-03-22 08:50:14.032	\N	3	92	3
772	f	Activo	2017-03-22 08:50:14.032	\N	4	92	3
773	f	Activo	2017-03-22 08:50:14.032	\N	6	92	3
774	f	Activo	2017-03-22 08:50:14.032	\N	1	94	3
775	f	Activo	2017-03-22 08:50:14.032	\N	2	94	3
776	f	Activo	2017-03-22 08:50:14.032	\N	3	94	3
777	f	Activo	2017-03-22 08:50:14.032	\N	4	94	3
778	f	Activo	2017-03-22 08:50:14.032	\N	6	94	3
779	f	Activo	2017-03-22 08:50:14.032	\N	1	96	3
780	f	Activo	2017-03-22 08:50:14.032	\N	2	96	3
781	f	Activo	2017-03-22 08:50:14.032	\N	3	96	3
782	f	Activo	2017-03-22 08:50:14.032	\N	4	96	3
783	f	Activo	2017-03-22 08:50:14.032	\N	6	96	3
784	f	Activo	2017-03-22 08:50:14.032	\N	1	97	3
785	f	Activo	2017-03-22 08:50:14.032	\N	2	97	3
786	f	Activo	2017-03-22 08:50:14.032	\N	3	97	3
787	f	Activo	2017-03-22 08:50:14.032	\N	4	97	3
788	f	Activo	2017-03-22 08:50:14.032	\N	6	97	3
789	f	Activo	2017-03-22 08:50:14.032	\N	1	99	3
790	f	Activo	2017-03-22 08:50:14.032	\N	2	99	3
791	f	Activo	2017-03-22 08:50:14.032	\N	3	99	3
792	f	Activo	2017-03-22 08:50:14.032	\N	4	99	3
793	f	Activo	2017-03-22 08:50:14.032	\N	6	99	3
794	f	Activo	2017-03-22 08:50:14.032	\N	1	100	3
795	f	Activo	2017-03-22 08:50:14.032	\N	2	100	3
796	f	Activo	2017-03-22 08:50:14.032	\N	3	100	3
797	f	Activo	2017-03-22 08:50:14.032	\N	4	100	3
798	f	Activo	2017-03-22 08:50:14.032	\N	6	100	3
799	f	Activo	2017-03-22 08:50:14.032	\N	1	101	3
800	f	Activo	2017-03-22 08:50:14.032	\N	2	101	3
801	f	Activo	2017-03-22 08:50:14.032	\N	3	101	3
802	f	Activo	2017-03-22 08:50:14.032	\N	4	101	3
803	f	Activo	2017-03-22 08:50:14.032	\N	6	101	3
804	f	Activo	2017-03-22 08:50:14.136	\N	5	4	3
805	f	Activo	2017-03-22 08:50:14.136	\N	5	3	3
806	f	Activo	2017-03-22 08:50:14.136	\N	5	2	3
807	f	Activo	2017-03-22 08:50:14.136	\N	5	14	3
808	f	Activo	2017-03-22 08:50:14.136	\N	5	38	3
809	f	Activo	2017-03-22 08:50:14.136	\N	5	40	3
810	f	Activo	2017-03-22 08:50:14.136	\N	5	44	3
811	f	Activo	2017-03-22 08:50:14.136	\N	5	48	3
812	f	Activo	2017-03-22 08:50:14.136	\N	5	54	3
813	f	Activo	2017-03-22 08:50:14.136	\N	5	58	3
814	f	Activo	2017-03-22 08:50:14.136	\N	5	57	3
815	f	Activo	2017-03-22 08:50:14.136	\N	5	61	3
816	f	Activo	2017-03-22 08:50:14.136	\N	5	65	3
817	f	Activo	2017-03-22 08:50:14.136	\N	5	64	3
818	f	Activo	2017-03-22 08:50:14.136	\N	5	67	3
819	f	Activo	2017-03-22 08:50:14.136	\N	5	70	3
820	f	Activo	2017-03-22 08:50:14.136	\N	5	78	3
821	f	Activo	2017-03-22 08:50:14.136	\N	5	81	3
822	f	Activo	2017-03-22 08:50:14.136	\N	5	84	3
823	f	Activo	2017-03-22 08:50:14.136	\N	5	83	3
824	f	Activo	2017-03-22 08:50:14.136	\N	5	93	3
825	f	Activo	2017-03-22 08:50:14.136	\N	5	95	3
826	f	Activo	2017-03-22 08:50:14.136	\N	5	98	3
827	f	Activo	2017-03-22 08:50:19.321	\N	1	1	4
828	f	Activo	2017-03-22 08:50:19.321	\N	2	1	4
829	f	Activo	2017-03-22 08:50:19.321	\N	3	1	4
830	f	Activo	2017-03-22 08:50:19.321	\N	4	1	4
831	f	Activo	2017-03-22 08:50:19.321	\N	6	1	4
832	f	Activo	2017-03-22 08:50:19.321	\N	1	5	4
833	f	Activo	2017-03-22 08:50:19.321	\N	2	5	4
834	f	Activo	2017-03-22 08:50:19.321	\N	3	5	4
835	f	Activo	2017-03-22 08:50:19.321	\N	4	5	4
836	f	Activo	2017-03-22 08:50:19.321	\N	6	5	4
837	f	Activo	2017-03-22 08:50:19.321	\N	1	6	4
838	f	Activo	2017-03-22 08:50:19.321	\N	2	6	4
839	f	Activo	2017-03-22 08:50:19.321	\N	3	6	4
840	f	Activo	2017-03-22 08:50:19.321	\N	4	6	4
841	f	Activo	2017-03-22 08:50:19.321	\N	6	6	4
842	f	Activo	2017-03-22 08:50:19.321	\N	1	7	4
843	f	Activo	2017-03-22 08:50:19.321	\N	2	7	4
844	f	Activo	2017-03-22 08:50:19.321	\N	3	7	4
845	f	Activo	2017-03-22 08:50:19.321	\N	4	7	4
846	f	Activo	2017-03-22 08:50:19.321	\N	6	7	4
847	f	Activo	2017-03-22 08:50:19.321	\N	1	8	4
848	f	Activo	2017-03-22 08:50:19.321	\N	2	8	4
849	f	Activo	2017-03-22 08:50:19.321	\N	3	8	4
850	f	Activo	2017-03-22 08:50:19.321	\N	4	8	4
851	f	Activo	2017-03-22 08:50:19.321	\N	6	8	4
852	f	Activo	2017-03-22 08:50:19.321	\N	1	9	4
853	f	Activo	2017-03-22 08:50:19.321	\N	2	9	4
854	f	Activo	2017-03-22 08:50:19.321	\N	3	9	4
855	f	Activo	2017-03-22 08:50:19.321	\N	4	9	4
856	f	Activo	2017-03-22 08:50:19.321	\N	6	9	4
857	f	Activo	2017-03-22 08:50:19.321	\N	1	10	4
858	f	Activo	2017-03-22 08:50:19.321	\N	2	10	4
859	f	Activo	2017-03-22 08:50:19.321	\N	3	10	4
860	f	Activo	2017-03-22 08:50:19.321	\N	4	10	4
861	f	Activo	2017-03-22 08:50:19.321	\N	6	10	4
862	f	Activo	2017-03-22 08:50:19.321	\N	1	11	4
863	f	Activo	2017-03-22 08:50:19.321	\N	2	11	4
864	f	Activo	2017-03-22 08:50:19.321	\N	3	11	4
865	f	Activo	2017-03-22 08:50:19.321	\N	4	11	4
866	f	Activo	2017-03-22 08:50:19.321	\N	6	11	4
867	f	Activo	2017-03-22 08:50:19.321	\N	1	12	4
868	f	Activo	2017-03-22 08:50:19.321	\N	2	12	4
869	f	Activo	2017-03-22 08:50:19.321	\N	3	12	4
870	f	Activo	2017-03-22 08:50:19.321	\N	4	12	4
871	f	Activo	2017-03-22 08:50:19.321	\N	6	12	4
872	f	Activo	2017-03-22 08:50:19.321	\N	1	13	4
873	f	Activo	2017-03-22 08:50:19.321	\N	2	13	4
874	f	Activo	2017-03-22 08:50:19.321	\N	3	13	4
875	f	Activo	2017-03-22 08:50:19.321	\N	4	13	4
876	f	Activo	2017-03-22 08:50:19.321	\N	6	13	4
877	f	Activo	2017-03-22 08:50:19.321	\N	1	15	4
878	f	Activo	2017-03-22 08:50:19.321	\N	2	15	4
879	f	Activo	2017-03-22 08:50:19.321	\N	3	15	4
880	f	Activo	2017-03-22 08:50:19.321	\N	4	15	4
881	f	Activo	2017-03-22 08:50:19.321	\N	6	15	4
882	f	Activo	2017-03-22 08:50:19.321	\N	1	16	4
883	f	Activo	2017-03-22 08:50:19.321	\N	2	16	4
884	f	Activo	2017-03-22 08:50:19.321	\N	3	16	4
885	f	Activo	2017-03-22 08:50:19.321	\N	4	16	4
886	f	Activo	2017-03-22 08:50:19.321	\N	6	16	4
887	f	Activo	2017-03-22 08:50:19.321	\N	1	17	4
888	f	Activo	2017-03-22 08:50:19.321	\N	2	17	4
889	f	Activo	2017-03-22 08:50:19.321	\N	3	17	4
890	f	Activo	2017-03-22 08:50:19.321	\N	4	17	4
891	f	Activo	2017-03-22 08:50:19.321	\N	6	17	4
892	f	Activo	2017-03-22 08:50:19.321	\N	1	18	4
893	f	Activo	2017-03-22 08:50:19.321	\N	2	18	4
894	f	Activo	2017-03-22 08:50:19.321	\N	3	18	4
895	f	Activo	2017-03-22 08:50:19.321	\N	4	18	4
896	f	Activo	2017-03-22 08:50:19.321	\N	6	18	4
897	f	Activo	2017-03-22 08:50:19.321	\N	1	19	4
898	f	Activo	2017-03-22 08:50:19.321	\N	2	19	4
899	f	Activo	2017-03-22 08:50:19.321	\N	3	19	4
900	f	Activo	2017-03-22 08:50:19.321	\N	4	19	4
901	f	Activo	2017-03-22 08:50:19.321	\N	6	19	4
902	f	Activo	2017-03-22 08:50:19.321	\N	1	20	4
903	f	Activo	2017-03-22 08:50:19.321	\N	2	20	4
904	f	Activo	2017-03-22 08:50:19.321	\N	3	20	4
905	f	Activo	2017-03-22 08:50:19.321	\N	4	20	4
906	f	Activo	2017-03-22 08:50:19.321	\N	6	20	4
907	f	Activo	2017-03-22 08:50:19.321	\N	1	21	4
908	f	Activo	2017-03-22 08:50:19.321	\N	2	21	4
909	f	Activo	2017-03-22 08:50:19.321	\N	3	21	4
910	f	Activo	2017-03-22 08:50:19.321	\N	4	21	4
911	f	Activo	2017-03-22 08:50:19.321	\N	6	21	4
912	f	Activo	2017-03-22 08:50:19.321	\N	1	22	4
913	f	Activo	2017-03-22 08:50:19.321	\N	2	22	4
914	f	Activo	2017-03-22 08:50:19.321	\N	3	22	4
915	f	Activo	2017-03-22 08:50:19.321	\N	4	22	4
916	f	Activo	2017-03-22 08:50:19.321	\N	6	22	4
917	f	Activo	2017-03-22 08:50:19.321	\N	1	23	4
918	f	Activo	2017-03-22 08:50:19.321	\N	2	23	4
919	f	Activo	2017-03-22 08:50:19.321	\N	3	23	4
920	f	Activo	2017-03-22 08:50:19.321	\N	4	23	4
921	f	Activo	2017-03-22 08:50:19.321	\N	6	23	4
922	f	Activo	2017-03-22 08:50:19.321	\N	1	24	4
923	f	Activo	2017-03-22 08:50:19.321	\N	2	24	4
924	f	Activo	2017-03-22 08:50:19.321	\N	3	24	4
925	f	Activo	2017-03-22 08:50:19.321	\N	4	24	4
926	f	Activo	2017-03-22 08:50:19.321	\N	6	24	4
927	f	Activo	2017-03-22 08:50:19.321	\N	1	25	4
928	f	Activo	2017-03-22 08:50:19.321	\N	2	25	4
929	f	Activo	2017-03-22 08:50:19.321	\N	3	25	4
930	f	Activo	2017-03-22 08:50:19.321	\N	4	25	4
931	f	Activo	2017-03-22 08:50:19.321	\N	6	25	4
932	f	Activo	2017-03-22 08:50:19.321	\N	1	26	4
933	f	Activo	2017-03-22 08:50:19.321	\N	2	26	4
934	f	Activo	2017-03-22 08:50:19.321	\N	3	26	4
935	f	Activo	2017-03-22 08:50:19.321	\N	4	26	4
936	f	Activo	2017-03-22 08:50:19.321	\N	6	26	4
937	f	Activo	2017-03-22 08:50:19.321	\N	1	27	4
938	f	Activo	2017-03-22 08:50:19.321	\N	2	27	4
939	f	Activo	2017-03-22 08:50:19.321	\N	3	27	4
940	f	Activo	2017-03-22 08:50:19.321	\N	4	27	4
941	f	Activo	2017-03-22 08:50:19.321	\N	6	27	4
942	f	Activo	2017-03-22 08:50:19.321	\N	1	28	4
943	f	Activo	2017-03-22 08:50:19.321	\N	2	28	4
944	f	Activo	2017-03-22 08:50:19.321	\N	3	28	4
945	f	Activo	2017-03-22 08:50:19.321	\N	4	28	4
946	f	Activo	2017-03-22 08:50:19.321	\N	6	28	4
947	f	Activo	2017-03-22 08:50:19.321	\N	1	29	4
948	f	Activo	2017-03-22 08:50:19.321	\N	2	29	4
949	f	Activo	2017-03-22 08:50:19.321	\N	3	29	4
950	f	Activo	2017-03-22 08:50:19.321	\N	4	29	4
951	f	Activo	2017-03-22 08:50:19.321	\N	6	29	4
952	f	Activo	2017-03-22 08:50:19.321	\N	1	30	4
953	f	Activo	2017-03-22 08:50:19.321	\N	2	30	4
954	f	Activo	2017-03-22 08:50:19.321	\N	3	30	4
955	f	Activo	2017-03-22 08:50:19.321	\N	4	30	4
956	f	Activo	2017-03-22 08:50:19.321	\N	6	30	4
957	f	Activo	2017-03-22 08:50:19.321	\N	1	31	4
958	f	Activo	2017-03-22 08:50:19.321	\N	2	31	4
959	f	Activo	2017-03-22 08:50:19.321	\N	3	31	4
960	f	Activo	2017-03-22 08:50:19.322	\N	4	31	4
961	f	Activo	2017-03-22 08:50:19.322	\N	6	31	4
962	f	Activo	2017-03-22 08:50:19.322	\N	1	32	4
963	f	Activo	2017-03-22 08:50:19.322	\N	2	32	4
964	f	Activo	2017-03-22 08:50:19.322	\N	3	32	4
965	f	Activo	2017-03-22 08:50:19.322	\N	4	32	4
966	f	Activo	2017-03-22 08:50:19.322	\N	6	32	4
967	f	Activo	2017-03-22 08:50:19.322	\N	1	33	4
968	f	Activo	2017-03-22 08:50:19.322	\N	2	33	4
969	f	Activo	2017-03-22 08:50:19.322	\N	3	33	4
970	f	Activo	2017-03-22 08:50:19.322	\N	4	33	4
971	f	Activo	2017-03-22 08:50:19.322	\N	6	33	4
972	f	Activo	2017-03-22 08:50:19.322	\N	1	34	4
973	f	Activo	2017-03-22 08:50:19.322	\N	2	34	4
974	f	Activo	2017-03-22 08:50:19.322	\N	3	34	4
975	f	Activo	2017-03-22 08:50:19.322	\N	4	34	4
976	f	Activo	2017-03-22 08:50:19.322	\N	6	34	4
977	f	Activo	2017-03-22 08:50:19.322	\N	1	35	4
978	f	Activo	2017-03-22 08:50:19.322	\N	2	35	4
979	f	Activo	2017-03-22 08:50:19.322	\N	3	35	4
980	f	Activo	2017-03-22 08:50:19.322	\N	4	35	4
981	f	Activo	2017-03-22 08:50:19.322	\N	6	35	4
982	f	Activo	2017-03-22 08:50:19.322	\N	1	36	4
983	f	Activo	2017-03-22 08:50:19.322	\N	2	36	4
984	f	Activo	2017-03-22 08:50:19.322	\N	3	36	4
985	f	Activo	2017-03-22 08:50:19.322	\N	4	36	4
986	f	Activo	2017-03-22 08:50:19.322	\N	6	36	4
987	f	Activo	2017-03-22 08:50:19.322	\N	1	37	4
988	f	Activo	2017-03-22 08:50:19.322	\N	2	37	4
989	f	Activo	2017-03-22 08:50:19.322	\N	3	37	4
990	f	Activo	2017-03-22 08:50:19.322	\N	4	37	4
991	f	Activo	2017-03-22 08:50:19.322	\N	6	37	4
992	f	Activo	2017-03-22 08:50:19.322	\N	1	39	4
993	f	Activo	2017-03-22 08:50:19.322	\N	2	39	4
994	f	Activo	2017-03-22 08:50:19.322	\N	3	39	4
995	f	Activo	2017-03-22 08:50:19.322	\N	4	39	4
996	f	Activo	2017-03-22 08:50:19.322	\N	6	39	4
997	f	Activo	2017-03-22 08:50:19.322	\N	1	41	4
998	f	Activo	2017-03-22 08:50:19.322	\N	2	41	4
999	f	Activo	2017-03-22 08:50:19.322	\N	3	41	4
1000	f	Activo	2017-03-22 08:50:19.322	\N	4	41	4
1001	f	Activo	2017-03-22 08:50:19.322	\N	6	41	4
1002	f	Activo	2017-03-22 08:50:19.322	\N	1	42	4
1003	f	Activo	2017-03-22 08:50:19.322	\N	2	42	4
1004	f	Activo	2017-03-22 08:50:19.322	\N	3	42	4
1005	f	Activo	2017-03-22 08:50:19.322	\N	4	42	4
1006	f	Activo	2017-03-22 08:50:19.322	\N	6	42	4
1007	f	Activo	2017-03-22 08:50:19.322	\N	1	43	4
1008	f	Activo	2017-03-22 08:50:19.322	\N	2	43	4
1009	f	Activo	2017-03-22 08:50:19.322	\N	3	43	4
1010	f	Activo	2017-03-22 08:50:19.322	\N	4	43	4
1011	f	Activo	2017-03-22 08:50:19.322	\N	6	43	4
1012	f	Activo	2017-03-22 08:50:19.322	\N	1	45	4
1013	f	Activo	2017-03-22 08:50:19.322	\N	2	45	4
1014	f	Activo	2017-03-22 08:50:19.322	\N	3	45	4
1015	f	Activo	2017-03-22 08:50:19.322	\N	4	45	4
1016	f	Activo	2017-03-22 08:50:19.322	\N	6	45	4
1017	f	Activo	2017-03-22 08:50:19.322	\N	1	46	4
1018	f	Activo	2017-03-22 08:50:19.322	\N	2	46	4
1019	f	Activo	2017-03-22 08:50:19.322	\N	3	46	4
1020	f	Activo	2017-03-22 08:50:19.322	\N	4	46	4
1021	f	Activo	2017-03-22 08:50:19.322	\N	6	46	4
1022	f	Activo	2017-03-22 08:50:19.322	\N	1	47	4
1023	f	Activo	2017-03-22 08:50:19.322	\N	2	47	4
1024	f	Activo	2017-03-22 08:50:19.322	\N	3	47	4
1025	f	Activo	2017-03-22 08:50:19.322	\N	4	47	4
1026	f	Activo	2017-03-22 08:50:19.322	\N	6	47	4
1027	f	Activo	2017-03-22 08:50:19.322	\N	1	49	4
1028	f	Activo	2017-03-22 08:50:19.322	\N	2	49	4
1029	f	Activo	2017-03-22 08:50:19.322	\N	3	49	4
1030	f	Activo	2017-03-22 08:50:19.322	\N	4	49	4
1031	f	Activo	2017-03-22 08:50:19.322	\N	6	49	4
1032	f	Activo	2017-03-22 08:50:19.322	\N	1	50	4
1033	f	Activo	2017-03-22 08:50:19.322	\N	2	50	4
1034	f	Activo	2017-03-22 08:50:19.322	\N	3	50	4
1035	f	Activo	2017-03-22 08:50:19.322	\N	4	50	4
1036	f	Activo	2017-03-22 08:50:19.322	\N	6	50	4
1037	f	Activo	2017-03-22 08:50:19.322	\N	1	51	4
1038	f	Activo	2017-03-22 08:50:19.322	\N	2	51	4
1039	f	Activo	2017-03-22 08:50:19.322	\N	3	51	4
1040	f	Activo	2017-03-22 08:50:19.322	\N	4	51	4
1041	f	Activo	2017-03-22 08:50:19.322	\N	6	51	4
1042	f	Activo	2017-03-22 08:50:19.322	\N	1	52	4
1043	f	Activo	2017-03-22 08:50:19.322	\N	2	52	4
1044	f	Activo	2017-03-22 08:50:19.322	\N	3	52	4
1045	f	Activo	2017-03-22 08:50:19.322	\N	4	52	4
1046	f	Activo	2017-03-22 08:50:19.322	\N	6	52	4
1047	f	Activo	2017-03-22 08:50:19.322	\N	1	53	4
1048	f	Activo	2017-03-22 08:50:19.322	\N	2	53	4
1049	f	Activo	2017-03-22 08:50:19.322	\N	3	53	4
1050	f	Activo	2017-03-22 08:50:19.322	\N	4	53	4
1051	f	Activo	2017-03-22 08:50:19.322	\N	6	53	4
1052	f	Activo	2017-03-22 08:50:19.322	\N	1	55	4
1053	f	Activo	2017-03-22 08:50:19.322	\N	2	55	4
1054	f	Activo	2017-03-22 08:50:19.322	\N	3	55	4
1055	f	Activo	2017-03-22 08:50:19.322	\N	4	55	4
1056	f	Activo	2017-03-22 08:50:19.322	\N	6	55	4
1057	f	Activo	2017-03-22 08:50:19.322	\N	1	56	4
1058	f	Activo	2017-03-22 08:50:19.322	\N	2	56	4
1059	f	Activo	2017-03-22 08:50:19.322	\N	3	56	4
1060	f	Activo	2017-03-22 08:50:19.322	\N	4	56	4
1061	f	Activo	2017-03-22 08:50:19.322	\N	6	56	4
1062	f	Activo	2017-03-22 08:50:19.322	\N	1	59	4
1063	f	Activo	2017-03-22 08:50:19.322	\N	2	59	4
1064	f	Activo	2017-03-22 08:50:19.322	\N	3	59	4
1065	f	Activo	2017-03-22 08:50:19.322	\N	4	59	4
1066	f	Activo	2017-03-22 08:50:19.322	\N	6	59	4
1067	f	Activo	2017-03-22 08:50:19.322	\N	1	60	4
1068	f	Activo	2017-03-22 08:50:19.322	\N	2	60	4
1069	f	Activo	2017-03-22 08:50:19.322	\N	3	60	4
1070	f	Activo	2017-03-22 08:50:19.322	\N	4	60	4
1071	f	Activo	2017-03-22 08:50:19.322	\N	6	60	4
1072	f	Activo	2017-03-22 08:50:19.322	\N	1	62	4
1073	f	Activo	2017-03-22 08:50:19.322	\N	2	62	4
1074	f	Activo	2017-03-22 08:50:19.322	\N	3	62	4
1075	f	Activo	2017-03-22 08:50:19.322	\N	4	62	4
1076	f	Activo	2017-03-22 08:50:19.322	\N	6	62	4
1077	f	Activo	2017-03-22 08:50:19.322	\N	1	63	4
1078	f	Activo	2017-03-22 08:50:19.322	\N	2	63	4
1079	f	Activo	2017-03-22 08:50:19.322	\N	3	63	4
1080	f	Activo	2017-03-22 08:50:19.322	\N	4	63	4
1081	f	Activo	2017-03-22 08:50:19.322	\N	6	63	4
1082	f	Activo	2017-03-22 08:50:19.322	\N	1	66	4
1083	f	Activo	2017-03-22 08:50:19.322	\N	2	66	4
1084	f	Activo	2017-03-22 08:50:19.322	\N	3	66	4
1085	f	Activo	2017-03-22 08:50:19.322	\N	4	66	4
1086	f	Activo	2017-03-22 08:50:19.322	\N	6	66	4
1087	f	Activo	2017-03-22 08:50:19.322	\N	1	68	4
1088	f	Activo	2017-03-22 08:50:19.322	\N	2	68	4
1089	f	Activo	2017-03-22 08:50:19.322	\N	3	68	4
1090	f	Activo	2017-03-22 08:50:19.322	\N	4	68	4
1091	f	Activo	2017-03-22 08:50:19.322	\N	6	68	4
1092	f	Activo	2017-03-22 08:50:19.322	\N	1	69	4
1093	f	Activo	2017-03-22 08:50:19.322	\N	2	69	4
1094	f	Activo	2017-03-22 08:50:19.322	\N	3	69	4
1095	f	Activo	2017-03-22 08:50:19.322	\N	4	69	4
1096	f	Activo	2017-03-22 08:50:19.322	\N	6	69	4
1097	f	Activo	2017-03-22 08:50:19.322	\N	1	71	4
1098	f	Activo	2017-03-22 08:50:19.322	\N	2	71	4
1099	f	Activo	2017-03-22 08:50:19.322	\N	3	71	4
1100	f	Activo	2017-03-22 08:50:19.322	\N	4	71	4
1101	f	Activo	2017-03-22 08:50:19.322	\N	6	71	4
1102	f	Activo	2017-03-22 08:50:19.322	\N	1	72	4
1103	f	Activo	2017-03-22 08:50:19.322	\N	2	72	4
1104	f	Activo	2017-03-22 08:50:19.322	\N	3	72	4
1105	f	Activo	2017-03-22 08:50:19.322	\N	4	72	4
1106	f	Activo	2017-03-22 08:50:19.322	\N	6	72	4
1107	f	Activo	2017-03-22 08:50:19.322	\N	1	73	4
1108	f	Activo	2017-03-22 08:50:19.322	\N	2	73	4
1109	f	Activo	2017-03-22 08:50:19.322	\N	3	73	4
1110	f	Activo	2017-03-22 08:50:19.322	\N	4	73	4
1111	f	Activo	2017-03-22 08:50:19.322	\N	6	73	4
1112	f	Activo	2017-03-22 08:50:19.322	\N	1	74	4
1113	f	Activo	2017-03-22 08:50:19.322	\N	2	74	4
1114	f	Activo	2017-03-22 08:50:19.322	\N	3	74	4
1115	f	Activo	2017-03-22 08:50:19.322	\N	4	74	4
1116	f	Activo	2017-03-22 08:50:19.322	\N	6	74	4
1117	f	Activo	2017-03-22 08:50:19.322	\N	1	75	4
1118	f	Activo	2017-03-22 08:50:19.322	\N	2	75	4
1119	f	Activo	2017-03-22 08:50:19.322	\N	3	75	4
1120	f	Activo	2017-03-22 08:50:19.322	\N	4	75	4
1121	f	Activo	2017-03-22 08:50:19.322	\N	6	75	4
1122	f	Activo	2017-03-22 08:50:19.322	\N	1	76	4
1123	f	Activo	2017-03-22 08:50:19.322	\N	2	76	4
1124	f	Activo	2017-03-22 08:50:19.322	\N	3	76	4
1125	f	Activo	2017-03-22 08:50:19.322	\N	4	76	4
1126	f	Activo	2017-03-22 08:50:19.322	\N	6	76	4
1127	f	Activo	2017-03-22 08:50:19.322	\N	1	77	4
1128	f	Activo	2017-03-22 08:50:19.322	\N	2	77	4
1129	f	Activo	2017-03-22 08:50:19.322	\N	3	77	4
1130	f	Activo	2017-03-22 08:50:19.322	\N	4	77	4
1131	f	Activo	2017-03-22 08:50:19.322	\N	6	77	4
1132	f	Activo	2017-03-22 08:50:19.322	\N	1	79	4
1133	f	Activo	2017-03-22 08:50:19.322	\N	2	79	4
1134	f	Activo	2017-03-22 08:50:19.322	\N	3	79	4
1135	f	Activo	2017-03-22 08:50:19.322	\N	4	79	4
1136	f	Activo	2017-03-22 08:50:19.322	\N	6	79	4
1137	f	Activo	2017-03-22 08:50:19.322	\N	1	80	4
1138	f	Activo	2017-03-22 08:50:19.322	\N	2	80	4
1139	f	Activo	2017-03-22 08:50:19.322	\N	3	80	4
1140	f	Activo	2017-03-22 08:50:19.322	\N	4	80	4
1141	f	Activo	2017-03-22 08:50:19.322	\N	6	80	4
1142	f	Activo	2017-03-22 08:50:19.322	\N	1	82	4
1143	f	Activo	2017-03-22 08:50:19.322	\N	2	82	4
1144	f	Activo	2017-03-22 08:50:19.322	\N	3	82	4
1145	f	Activo	2017-03-22 08:50:19.322	\N	4	82	4
1146	f	Activo	2017-03-22 08:50:19.322	\N	6	82	4
1147	f	Activo	2017-03-22 08:50:19.322	\N	1	85	4
1148	f	Activo	2017-03-22 08:50:19.322	\N	2	85	4
1149	f	Activo	2017-03-22 08:50:19.322	\N	3	85	4
1150	f	Activo	2017-03-22 08:50:19.322	\N	4	85	4
1151	f	Activo	2017-03-22 08:50:19.322	\N	6	85	4
1152	f	Activo	2017-03-22 08:50:19.322	\N	1	86	4
1153	f	Activo	2017-03-22 08:50:19.322	\N	2	86	4
1154	f	Activo	2017-03-22 08:50:19.322	\N	3	86	4
1155	f	Activo	2017-03-22 08:50:19.322	\N	4	86	4
1156	f	Activo	2017-03-22 08:50:19.322	\N	6	86	4
1157	f	Activo	2017-03-22 08:50:19.322	\N	1	87	4
1158	f	Activo	2017-03-22 08:50:19.322	\N	2	87	4
1159	f	Activo	2017-03-22 08:50:19.322	\N	3	87	4
1160	f	Activo	2017-03-22 08:50:19.322	\N	4	87	4
1161	f	Activo	2017-03-22 08:50:19.322	\N	6	87	4
1162	f	Activo	2017-03-22 08:50:19.322	\N	1	88	4
1163	f	Activo	2017-03-22 08:50:19.322	\N	2	88	4
1164	f	Activo	2017-03-22 08:50:19.322	\N	3	88	4
1165	f	Activo	2017-03-22 08:50:19.322	\N	4	88	4
1166	f	Activo	2017-03-22 08:50:19.322	\N	6	88	4
1167	f	Activo	2017-03-22 08:50:19.322	\N	1	89	4
1168	f	Activo	2017-03-22 08:50:19.322	\N	2	89	4
1169	f	Activo	2017-03-22 08:50:19.322	\N	3	89	4
1170	f	Activo	2017-03-22 08:50:19.322	\N	4	89	4
1171	f	Activo	2017-03-22 08:50:19.322	\N	6	89	4
1172	f	Activo	2017-03-22 08:50:19.322	\N	1	90	4
1173	f	Activo	2017-03-22 08:50:19.322	\N	2	90	4
1174	f	Activo	2017-03-22 08:50:19.322	\N	3	90	4
1175	f	Activo	2017-03-22 08:50:19.322	\N	4	90	4
1176	f	Activo	2017-03-22 08:50:19.322	\N	6	90	4
1177	f	Activo	2017-03-22 08:50:19.322	\N	1	91	4
1178	f	Activo	2017-03-22 08:50:19.322	\N	2	91	4
1179	f	Activo	2017-03-22 08:50:19.322	\N	3	91	4
1180	f	Activo	2017-03-22 08:50:19.322	\N	4	91	4
1181	f	Activo	2017-03-22 08:50:19.322	\N	6	91	4
1182	f	Activo	2017-03-22 08:50:19.322	\N	1	92	4
1183	f	Activo	2017-03-22 08:50:19.322	\N	2	92	4
1184	f	Activo	2017-03-22 08:50:19.322	\N	3	92	4
1185	f	Activo	2017-03-22 08:50:19.322	\N	4	92	4
1186	f	Activo	2017-03-22 08:50:19.322	\N	6	92	4
1187	f	Activo	2017-03-22 08:50:19.322	\N	1	94	4
1188	f	Activo	2017-03-22 08:50:19.322	\N	2	94	4
1189	f	Activo	2017-03-22 08:50:19.322	\N	3	94	4
1190	f	Activo	2017-03-22 08:50:19.322	\N	4	94	4
1191	f	Activo	2017-03-22 08:50:19.322	\N	6	94	4
1192	f	Activo	2017-03-22 08:50:19.322	\N	1	96	4
1193	f	Activo	2017-03-22 08:50:19.322	\N	2	96	4
1194	f	Activo	2017-03-22 08:50:19.322	\N	3	96	4
1195	f	Activo	2017-03-22 08:50:19.322	\N	4	96	4
1196	f	Activo	2017-03-22 08:50:19.322	\N	6	96	4
1197	f	Activo	2017-03-22 08:50:19.322	\N	1	97	4
1198	f	Activo	2017-03-22 08:50:19.322	\N	2	97	4
1199	f	Activo	2017-03-22 08:50:19.322	\N	3	97	4
1200	f	Activo	2017-03-22 08:50:19.322	\N	4	97	4
1201	f	Activo	2017-03-22 08:50:19.322	\N	6	97	4
1202	f	Activo	2017-03-22 08:50:19.322	\N	1	99	4
1203	f	Activo	2017-03-22 08:50:19.322	\N	2	99	4
1204	f	Activo	2017-03-22 08:50:19.322	\N	3	99	4
1205	f	Activo	2017-03-22 08:50:19.322	\N	4	99	4
1206	f	Activo	2017-03-22 08:50:19.322	\N	6	99	4
1207	f	Activo	2017-03-22 08:50:19.322	\N	1	100	4
1208	f	Activo	2017-03-22 08:50:19.322	\N	2	100	4
1209	f	Activo	2017-03-22 08:50:19.322	\N	3	100	4
1210	f	Activo	2017-03-22 08:50:19.322	\N	4	100	4
1211	f	Activo	2017-03-22 08:50:19.322	\N	6	100	4
1212	f	Activo	2017-03-22 08:50:19.322	\N	1	101	4
1213	f	Activo	2017-03-22 08:50:19.322	\N	2	101	4
1214	f	Activo	2017-03-22 08:50:19.322	\N	3	101	4
1215	f	Activo	2017-03-22 08:50:19.322	\N	4	101	4
1216	f	Activo	2017-03-22 08:50:19.322	\N	6	101	4
1217	f	Activo	2017-03-22 08:50:19.437	\N	5	4	4
1218	f	Activo	2017-03-22 08:50:19.437	\N	5	3	4
1219	f	Activo	2017-03-22 08:50:19.437	\N	5	2	4
1220	f	Activo	2017-03-22 08:50:19.437	\N	5	14	4
1221	f	Activo	2017-03-22 08:50:19.437	\N	5	38	4
1222	f	Activo	2017-03-22 08:50:19.437	\N	5	40	4
1223	f	Activo	2017-03-22 08:50:19.437	\N	5	44	4
1224	f	Activo	2017-03-22 08:50:19.437	\N	5	48	4
1225	f	Activo	2017-03-22 08:50:19.437	\N	5	54	4
1226	f	Activo	2017-03-22 08:50:19.437	\N	5	58	4
1227	f	Activo	2017-03-22 08:50:19.437	\N	5	57	4
1228	f	Activo	2017-03-22 08:50:19.437	\N	5	61	4
1229	f	Activo	2017-03-22 08:50:19.437	\N	5	65	4
1230	f	Activo	2017-03-22 08:50:19.437	\N	5	64	4
1231	f	Activo	2017-03-22 08:50:19.437	\N	5	67	4
1232	f	Activo	2017-03-22 08:50:19.437	\N	5	70	4
1233	f	Activo	2017-03-22 08:50:19.437	\N	5	78	4
1234	f	Activo	2017-03-22 08:50:19.437	\N	5	81	4
1235	f	Activo	2017-03-22 08:50:19.437	\N	5	84	4
1236	f	Activo	2017-03-22 08:50:19.437	\N	5	83	4
1237	f	Activo	2017-03-22 08:50:19.437	\N	5	93	4
1238	f	Activo	2017-03-22 08:50:19.437	\N	5	95	4
1239	f	Activo	2017-03-22 08:50:19.437	\N	5	98	4
1240	f	Activo	2017-03-22 08:50:24.769	\N	1	1	5
1241	f	Activo	2017-03-22 08:50:24.769	\N	2	1	5
1242	f	Activo	2017-03-22 08:50:24.769	\N	3	1	5
1243	f	Activo	2017-03-22 08:50:24.769	\N	4	1	5
1244	f	Activo	2017-03-22 08:50:24.769	\N	6	1	5
1245	f	Activo	2017-03-22 08:50:24.769	\N	1	5	5
1246	f	Activo	2017-03-22 08:50:24.769	\N	2	5	5
1247	f	Activo	2017-03-22 08:50:24.769	\N	3	5	5
1248	f	Activo	2017-03-22 08:50:24.769	\N	4	5	5
1249	f	Activo	2017-03-22 08:50:24.769	\N	6	5	5
1250	f	Activo	2017-03-22 08:50:24.769	\N	1	6	5
1251	f	Activo	2017-03-22 08:50:24.769	\N	2	6	5
1252	f	Activo	2017-03-22 08:50:24.769	\N	3	6	5
1253	f	Activo	2017-03-22 08:50:24.769	\N	4	6	5
1254	f	Activo	2017-03-22 08:50:24.769	\N	6	6	5
1255	f	Activo	2017-03-22 08:50:24.769	\N	1	7	5
1256	f	Activo	2017-03-22 08:50:24.769	\N	2	7	5
1257	f	Activo	2017-03-22 08:50:24.769	\N	3	7	5
1258	f	Activo	2017-03-22 08:50:24.769	\N	4	7	5
1259	f	Activo	2017-03-22 08:50:24.769	\N	6	7	5
1260	f	Activo	2017-03-22 08:50:24.769	\N	1	8	5
1261	f	Activo	2017-03-22 08:50:24.769	\N	2	8	5
1262	f	Activo	2017-03-22 08:50:24.769	\N	3	8	5
1263	f	Activo	2017-03-22 08:50:24.769	\N	4	8	5
1264	f	Activo	2017-03-22 08:50:24.769	\N	6	8	5
1265	f	Activo	2017-03-22 08:50:24.769	\N	1	9	5
1266	f	Activo	2017-03-22 08:50:24.769	\N	2	9	5
1267	f	Activo	2017-03-22 08:50:24.769	\N	3	9	5
1268	f	Activo	2017-03-22 08:50:24.769	\N	4	9	5
1269	f	Activo	2017-03-22 08:50:24.769	\N	6	9	5
1270	f	Activo	2017-03-22 08:50:24.769	\N	1	10	5
1271	f	Activo	2017-03-22 08:50:24.769	\N	2	10	5
1272	f	Activo	2017-03-22 08:50:24.769	\N	3	10	5
1273	f	Activo	2017-03-22 08:50:24.769	\N	4	10	5
1274	f	Activo	2017-03-22 08:50:24.769	\N	6	10	5
1275	f	Activo	2017-03-22 08:50:24.769	\N	1	11	5
1276	f	Activo	2017-03-22 08:50:24.769	\N	2	11	5
1277	f	Activo	2017-03-22 08:50:24.769	\N	3	11	5
1278	f	Activo	2017-03-22 08:50:24.769	\N	4	11	5
1279	f	Activo	2017-03-22 08:50:24.769	\N	6	11	5
1280	f	Activo	2017-03-22 08:50:24.769	\N	1	12	5
1281	f	Activo	2017-03-22 08:50:24.769	\N	2	12	5
1282	f	Activo	2017-03-22 08:50:24.769	\N	3	12	5
1283	f	Activo	2017-03-22 08:50:24.769	\N	4	12	5
1284	f	Activo	2017-03-22 08:50:24.769	\N	6	12	5
1285	f	Activo	2017-03-22 08:50:24.769	\N	1	13	5
1286	f	Activo	2017-03-22 08:50:24.769	\N	2	13	5
1287	f	Activo	2017-03-22 08:50:24.769	\N	3	13	5
1288	f	Activo	2017-03-22 08:50:24.769	\N	4	13	5
1289	f	Activo	2017-03-22 08:50:24.769	\N	6	13	5
1290	f	Activo	2017-03-22 08:50:24.769	\N	1	15	5
1291	f	Activo	2017-03-22 08:50:24.769	\N	2	15	5
1292	f	Activo	2017-03-22 08:50:24.769	\N	3	15	5
1293	f	Activo	2017-03-22 08:50:24.769	\N	4	15	5
1294	f	Activo	2017-03-22 08:50:24.769	\N	6	15	5
1295	f	Activo	2017-03-22 08:50:24.769	\N	1	16	5
1296	f	Activo	2017-03-22 08:50:24.769	\N	2	16	5
1297	f	Activo	2017-03-22 08:50:24.769	\N	3	16	5
1298	f	Activo	2017-03-22 08:50:24.769	\N	4	16	5
1299	f	Activo	2017-03-22 08:50:24.769	\N	6	16	5
1300	f	Activo	2017-03-22 08:50:24.769	\N	1	17	5
1301	f	Activo	2017-03-22 08:50:24.769	\N	2	17	5
1302	f	Activo	2017-03-22 08:50:24.769	\N	3	17	5
1303	f	Activo	2017-03-22 08:50:24.769	\N	4	17	5
1304	f	Activo	2017-03-22 08:50:24.769	\N	6	17	5
1305	f	Activo	2017-03-22 08:50:24.769	\N	1	18	5
1306	f	Activo	2017-03-22 08:50:24.769	\N	2	18	5
1307	f	Activo	2017-03-22 08:50:24.769	\N	3	18	5
1308	f	Activo	2017-03-22 08:50:24.769	\N	4	18	5
1309	f	Activo	2017-03-22 08:50:24.769	\N	6	18	5
1310	f	Activo	2017-03-22 08:50:24.769	\N	1	19	5
1311	f	Activo	2017-03-22 08:50:24.769	\N	2	19	5
1312	f	Activo	2017-03-22 08:50:24.769	\N	3	19	5
1313	f	Activo	2017-03-22 08:50:24.769	\N	4	19	5
1314	f	Activo	2017-03-22 08:50:24.769	\N	6	19	5
1315	f	Activo	2017-03-22 08:50:24.769	\N	1	20	5
1316	f	Activo	2017-03-22 08:50:24.769	\N	2	20	5
1317	f	Activo	2017-03-22 08:50:24.769	\N	3	20	5
1318	f	Activo	2017-03-22 08:50:24.769	\N	4	20	5
1319	f	Activo	2017-03-22 08:50:24.769	\N	6	20	5
1320	f	Activo	2017-03-22 08:50:24.769	\N	1	21	5
1321	f	Activo	2017-03-22 08:50:24.769	\N	2	21	5
1322	f	Activo	2017-03-22 08:50:24.769	\N	3	21	5
1323	f	Activo	2017-03-22 08:50:24.769	\N	4	21	5
1324	f	Activo	2017-03-22 08:50:24.769	\N	6	21	5
1325	f	Activo	2017-03-22 08:50:24.769	\N	1	22	5
1326	f	Activo	2017-03-22 08:50:24.769	\N	2	22	5
1327	f	Activo	2017-03-22 08:50:24.769	\N	3	22	5
1328	f	Activo	2017-03-22 08:50:24.769	\N	4	22	5
1329	f	Activo	2017-03-22 08:50:24.769	\N	6	22	5
1330	f	Activo	2017-03-22 08:50:24.769	\N	1	23	5
1331	f	Activo	2017-03-22 08:50:24.769	\N	2	23	5
1332	f	Activo	2017-03-22 08:50:24.769	\N	3	23	5
1333	f	Activo	2017-03-22 08:50:24.769	\N	4	23	5
1334	f	Activo	2017-03-22 08:50:24.769	\N	6	23	5
1335	f	Activo	2017-03-22 08:50:24.769	\N	1	24	5
1336	f	Activo	2017-03-22 08:50:24.769	\N	2	24	5
1337	f	Activo	2017-03-22 08:50:24.769	\N	3	24	5
1338	f	Activo	2017-03-22 08:50:24.769	\N	4	24	5
1339	f	Activo	2017-03-22 08:50:24.769	\N	6	24	5
1340	f	Activo	2017-03-22 08:50:24.769	\N	1	25	5
1341	f	Activo	2017-03-22 08:50:24.769	\N	2	25	5
1342	f	Activo	2017-03-22 08:50:24.769	\N	3	25	5
1343	f	Activo	2017-03-22 08:50:24.769	\N	4	25	5
1344	f	Activo	2017-03-22 08:50:24.769	\N	6	25	5
1345	f	Activo	2017-03-22 08:50:24.769	\N	1	26	5
1346	f	Activo	2017-03-22 08:50:24.769	\N	2	26	5
1347	f	Activo	2017-03-22 08:50:24.769	\N	3	26	5
1348	f	Activo	2017-03-22 08:50:24.769	\N	4	26	5
1349	f	Activo	2017-03-22 08:50:24.769	\N	6	26	5
1350	f	Activo	2017-03-22 08:50:24.769	\N	1	27	5
1351	f	Activo	2017-03-22 08:50:24.769	\N	2	27	5
1352	f	Activo	2017-03-22 08:50:24.769	\N	3	27	5
1353	f	Activo	2017-03-22 08:50:24.769	\N	4	27	5
1354	f	Activo	2017-03-22 08:50:24.769	\N	6	27	5
1355	f	Activo	2017-03-22 08:50:24.769	\N	1	28	5
1356	f	Activo	2017-03-22 08:50:24.769	\N	2	28	5
1357	f	Activo	2017-03-22 08:50:24.769	\N	3	28	5
1358	f	Activo	2017-03-22 08:50:24.769	\N	4	28	5
1359	f	Activo	2017-03-22 08:50:24.769	\N	6	28	5
1360	f	Activo	2017-03-22 08:50:24.769	\N	1	29	5
1361	f	Activo	2017-03-22 08:50:24.769	\N	2	29	5
1362	f	Activo	2017-03-22 08:50:24.769	\N	3	29	5
1363	f	Activo	2017-03-22 08:50:24.769	\N	4	29	5
1364	f	Activo	2017-03-22 08:50:24.769	\N	6	29	5
1365	f	Activo	2017-03-22 08:50:24.769	\N	1	30	5
1366	f	Activo	2017-03-22 08:50:24.769	\N	2	30	5
1367	f	Activo	2017-03-22 08:50:24.769	\N	3	30	5
1368	f	Activo	2017-03-22 08:50:24.769	\N	4	30	5
1369	f	Activo	2017-03-22 08:50:24.769	\N	6	30	5
1370	f	Activo	2017-03-22 08:50:24.769	\N	1	31	5
1371	f	Activo	2017-03-22 08:50:24.769	\N	2	31	5
1372	f	Activo	2017-03-22 08:50:24.769	\N	3	31	5
1373	f	Activo	2017-03-22 08:50:24.769	\N	4	31	5
1374	f	Activo	2017-03-22 08:50:24.769	\N	6	31	5
1375	f	Activo	2017-03-22 08:50:24.769	\N	1	32	5
1376	f	Activo	2017-03-22 08:50:24.769	\N	2	32	5
1377	f	Activo	2017-03-22 08:50:24.769	\N	3	32	5
1378	f	Activo	2017-03-22 08:50:24.769	\N	4	32	5
1379	f	Activo	2017-03-22 08:50:24.769	\N	6	32	5
1380	f	Activo	2017-03-22 08:50:24.769	\N	1	33	5
1381	f	Activo	2017-03-22 08:50:24.77	\N	2	33	5
1382	f	Activo	2017-03-22 08:50:24.77	\N	3	33	5
1383	f	Activo	2017-03-22 08:50:24.77	\N	4	33	5
1384	f	Activo	2017-03-22 08:50:24.77	\N	6	33	5
1385	f	Activo	2017-03-22 08:50:24.77	\N	1	34	5
1386	f	Activo	2017-03-22 08:50:24.77	\N	2	34	5
1387	f	Activo	2017-03-22 08:50:24.77	\N	3	34	5
1388	f	Activo	2017-03-22 08:50:24.77	\N	4	34	5
1389	f	Activo	2017-03-22 08:50:24.77	\N	6	34	5
1390	f	Activo	2017-03-22 08:50:24.77	\N	1	35	5
1391	f	Activo	2017-03-22 08:50:24.77	\N	2	35	5
1392	f	Activo	2017-03-22 08:50:24.77	\N	3	35	5
1393	f	Activo	2017-03-22 08:50:24.77	\N	4	35	5
1394	f	Activo	2017-03-22 08:50:24.77	\N	6	35	5
1395	f	Activo	2017-03-22 08:50:24.77	\N	1	36	5
1396	f	Activo	2017-03-22 08:50:24.77	\N	2	36	5
1397	f	Activo	2017-03-22 08:50:24.77	\N	3	36	5
1398	f	Activo	2017-03-22 08:50:24.77	\N	4	36	5
1399	f	Activo	2017-03-22 08:50:24.77	\N	6	36	5
1400	f	Activo	2017-03-22 08:50:24.77	\N	1	37	5
1401	f	Activo	2017-03-22 08:50:24.77	\N	2	37	5
1402	f	Activo	2017-03-22 08:50:24.77	\N	3	37	5
1403	f	Activo	2017-03-22 08:50:24.77	\N	4	37	5
1404	f	Activo	2017-03-22 08:50:24.77	\N	6	37	5
1405	f	Activo	2017-03-22 08:50:24.77	\N	1	39	5
1406	f	Activo	2017-03-22 08:50:24.77	\N	2	39	5
1407	f	Activo	2017-03-22 08:50:24.77	\N	3	39	5
1408	f	Activo	2017-03-22 08:50:24.77	\N	4	39	5
1409	f	Activo	2017-03-22 08:50:24.77	\N	6	39	5
1410	f	Activo	2017-03-22 08:50:24.77	\N	1	41	5
1411	f	Activo	2017-03-22 08:50:24.77	\N	2	41	5
1412	f	Activo	2017-03-22 08:50:24.77	\N	3	41	5
1413	f	Activo	2017-03-22 08:50:24.77	\N	4	41	5
1414	f	Activo	2017-03-22 08:50:24.77	\N	6	41	5
1415	f	Activo	2017-03-22 08:50:24.77	\N	1	42	5
1416	f	Activo	2017-03-22 08:50:24.77	\N	2	42	5
1417	f	Activo	2017-03-22 08:50:24.77	\N	3	42	5
1418	f	Activo	2017-03-22 08:50:24.77	\N	4	42	5
1419	f	Activo	2017-03-22 08:50:24.77	\N	6	42	5
1420	f	Activo	2017-03-22 08:50:24.77	\N	1	43	5
1421	f	Activo	2017-03-22 08:50:24.77	\N	2	43	5
1422	f	Activo	2017-03-22 08:50:24.77	\N	3	43	5
1423	f	Activo	2017-03-22 08:50:24.77	\N	4	43	5
1424	f	Activo	2017-03-22 08:50:24.77	\N	6	43	5
1425	f	Activo	2017-03-22 08:50:24.77	\N	1	45	5
1426	f	Activo	2017-03-22 08:50:24.77	\N	2	45	5
1427	f	Activo	2017-03-22 08:50:24.77	\N	3	45	5
1428	f	Activo	2017-03-22 08:50:24.77	\N	4	45	5
1429	f	Activo	2017-03-22 08:50:24.77	\N	6	45	5
1430	f	Activo	2017-03-22 08:50:24.77	\N	1	46	5
1431	f	Activo	2017-03-22 08:50:24.77	\N	2	46	5
1432	f	Activo	2017-03-22 08:50:24.77	\N	3	46	5
1433	f	Activo	2017-03-22 08:50:24.77	\N	4	46	5
1434	f	Activo	2017-03-22 08:50:24.77	\N	6	46	5
1435	f	Activo	2017-03-22 08:50:24.77	\N	1	47	5
1436	f	Activo	2017-03-22 08:50:24.77	\N	2	47	5
1437	f	Activo	2017-03-22 08:50:24.77	\N	3	47	5
1438	f	Activo	2017-03-22 08:50:24.77	\N	4	47	5
1439	f	Activo	2017-03-22 08:50:24.77	\N	6	47	5
1440	f	Activo	2017-03-22 08:50:24.77	\N	1	49	5
1441	f	Activo	2017-03-22 08:50:24.77	\N	2	49	5
1442	f	Activo	2017-03-22 08:50:24.77	\N	3	49	5
1443	f	Activo	2017-03-22 08:50:24.77	\N	4	49	5
1444	f	Activo	2017-03-22 08:50:24.77	\N	6	49	5
1445	f	Activo	2017-03-22 08:50:24.77	\N	1	50	5
1446	f	Activo	2017-03-22 08:50:24.77	\N	2	50	5
1447	f	Activo	2017-03-22 08:50:24.77	\N	3	50	5
1448	f	Activo	2017-03-22 08:50:24.77	\N	4	50	5
1449	f	Activo	2017-03-22 08:50:24.77	\N	6	50	5
1450	f	Activo	2017-03-22 08:50:24.77	\N	1	51	5
1451	f	Activo	2017-03-22 08:50:24.77	\N	2	51	5
1452	f	Activo	2017-03-22 08:50:24.77	\N	3	51	5
1453	f	Activo	2017-03-22 08:50:24.77	\N	4	51	5
1454	f	Activo	2017-03-22 08:50:24.77	\N	6	51	5
1455	f	Activo	2017-03-22 08:50:24.77	\N	1	52	5
1456	f	Activo	2017-03-22 08:50:24.77	\N	2	52	5
1457	f	Activo	2017-03-22 08:50:24.77	\N	3	52	5
1458	f	Activo	2017-03-22 08:50:24.77	\N	4	52	5
1459	f	Activo	2017-03-22 08:50:24.77	\N	6	52	5
1460	f	Activo	2017-03-22 08:50:24.77	\N	1	53	5
1461	f	Activo	2017-03-22 08:50:24.77	\N	2	53	5
1462	f	Activo	2017-03-22 08:50:24.77	\N	3	53	5
1463	f	Activo	2017-03-22 08:50:24.77	\N	4	53	5
1464	f	Activo	2017-03-22 08:50:24.77	\N	6	53	5
1465	f	Activo	2017-03-22 08:50:24.77	\N	1	55	5
1466	f	Activo	2017-03-22 08:50:24.77	\N	2	55	5
1467	f	Activo	2017-03-22 08:50:24.77	\N	3	55	5
1468	f	Activo	2017-03-22 08:50:24.77	\N	4	55	5
1469	f	Activo	2017-03-22 08:50:24.77	\N	6	55	5
1470	f	Activo	2017-03-22 08:50:24.77	\N	1	56	5
1471	f	Activo	2017-03-22 08:50:24.77	\N	2	56	5
1472	f	Activo	2017-03-22 08:50:24.77	\N	3	56	5
1473	f	Activo	2017-03-22 08:50:24.77	\N	4	56	5
1474	f	Activo	2017-03-22 08:50:24.77	\N	6	56	5
1475	f	Activo	2017-03-22 08:50:24.77	\N	1	59	5
1476	f	Activo	2017-03-22 08:50:24.77	\N	2	59	5
1477	f	Activo	2017-03-22 08:50:24.77	\N	3	59	5
1478	f	Activo	2017-03-22 08:50:24.77	\N	4	59	5
1479	f	Activo	2017-03-22 08:50:24.77	\N	6	59	5
1480	f	Activo	2017-03-22 08:50:24.77	\N	1	60	5
1481	f	Activo	2017-03-22 08:50:24.77	\N	2	60	5
1482	f	Activo	2017-03-22 08:50:24.77	\N	3	60	5
1483	f	Activo	2017-03-22 08:50:24.77	\N	4	60	5
1484	f	Activo	2017-03-22 08:50:24.77	\N	6	60	5
1485	f	Activo	2017-03-22 08:50:24.77	\N	1	62	5
1486	f	Activo	2017-03-22 08:50:24.77	\N	2	62	5
1487	f	Activo	2017-03-22 08:50:24.77	\N	3	62	5
1488	f	Activo	2017-03-22 08:50:24.77	\N	4	62	5
1489	f	Activo	2017-03-22 08:50:24.77	\N	6	62	5
1490	f	Activo	2017-03-22 08:50:24.77	\N	1	63	5
1491	f	Activo	2017-03-22 08:50:24.77	\N	2	63	5
1492	f	Activo	2017-03-22 08:50:24.77	\N	3	63	5
1493	f	Activo	2017-03-22 08:50:24.77	\N	4	63	5
1494	f	Activo	2017-03-22 08:50:24.77	\N	6	63	5
1495	f	Activo	2017-03-22 08:50:24.77	\N	1	66	5
1496	f	Activo	2017-03-22 08:50:24.77	\N	2	66	5
1497	f	Activo	2017-03-22 08:50:24.77	\N	3	66	5
1498	f	Activo	2017-03-22 08:50:24.77	\N	4	66	5
1499	f	Activo	2017-03-22 08:50:24.77	\N	6	66	5
1500	f	Activo	2017-03-22 08:50:24.77	\N	1	68	5
1501	f	Activo	2017-03-22 08:50:24.77	\N	2	68	5
1502	f	Activo	2017-03-22 08:50:24.77	\N	3	68	5
1503	f	Activo	2017-03-22 08:50:24.77	\N	4	68	5
1504	f	Activo	2017-03-22 08:50:24.77	\N	6	68	5
1505	f	Activo	2017-03-22 08:50:24.77	\N	1	69	5
1506	f	Activo	2017-03-22 08:50:24.77	\N	2	69	5
1507	f	Activo	2017-03-22 08:50:24.77	\N	3	69	5
1508	f	Activo	2017-03-22 08:50:24.77	\N	4	69	5
1509	f	Activo	2017-03-22 08:50:24.77	\N	6	69	5
1510	f	Activo	2017-03-22 08:50:24.77	\N	1	71	5
1511	f	Activo	2017-03-22 08:50:24.77	\N	2	71	5
1512	f	Activo	2017-03-22 08:50:24.77	\N	3	71	5
1513	f	Activo	2017-03-22 08:50:24.77	\N	4	71	5
1514	f	Activo	2017-03-22 08:50:24.77	\N	6	71	5
1515	f	Activo	2017-03-22 08:50:24.77	\N	1	72	5
1516	f	Activo	2017-03-22 08:50:24.77	\N	2	72	5
1517	f	Activo	2017-03-22 08:50:24.77	\N	3	72	5
1518	f	Activo	2017-03-22 08:50:24.77	\N	4	72	5
1519	f	Activo	2017-03-22 08:50:24.77	\N	6	72	5
1520	f	Activo	2017-03-22 08:50:24.77	\N	1	73	5
1521	f	Activo	2017-03-22 08:50:24.77	\N	2	73	5
1522	f	Activo	2017-03-22 08:50:24.77	\N	3	73	5
1523	f	Activo	2017-03-22 08:50:24.77	\N	4	73	5
1524	f	Activo	2017-03-22 08:50:24.77	\N	6	73	5
1525	f	Activo	2017-03-22 08:50:24.77	\N	1	74	5
1526	f	Activo	2017-03-22 08:50:24.77	\N	2	74	5
1527	f	Activo	2017-03-22 08:50:24.77	\N	3	74	5
1528	f	Activo	2017-03-22 08:50:24.77	\N	4	74	5
1529	f	Activo	2017-03-22 08:50:24.77	\N	6	74	5
1530	f	Activo	2017-03-22 08:50:24.77	\N	1	75	5
1531	f	Activo	2017-03-22 08:50:24.77	\N	2	75	5
1532	f	Activo	2017-03-22 08:50:24.77	\N	3	75	5
1533	f	Activo	2017-03-22 08:50:24.77	\N	4	75	5
1534	f	Activo	2017-03-22 08:50:24.77	\N	6	75	5
1535	f	Activo	2017-03-22 08:50:24.77	\N	1	76	5
1536	f	Activo	2017-03-22 08:50:24.77	\N	2	76	5
1537	f	Activo	2017-03-22 08:50:24.77	\N	3	76	5
1538	f	Activo	2017-03-22 08:50:24.77	\N	4	76	5
1539	f	Activo	2017-03-22 08:50:24.77	\N	6	76	5
1540	f	Activo	2017-03-22 08:50:24.77	\N	1	77	5
1541	f	Activo	2017-03-22 08:50:24.77	\N	2	77	5
1542	f	Activo	2017-03-22 08:50:24.77	\N	3	77	5
1543	f	Activo	2017-03-22 08:50:24.77	\N	4	77	5
1544	f	Activo	2017-03-22 08:50:24.77	\N	6	77	5
1545	f	Activo	2017-03-22 08:50:24.77	\N	1	79	5
1546	f	Activo	2017-03-22 08:50:24.77	\N	2	79	5
1547	f	Activo	2017-03-22 08:50:24.77	\N	3	79	5
1548	f	Activo	2017-03-22 08:50:24.77	\N	4	79	5
1549	f	Activo	2017-03-22 08:50:24.77	\N	6	79	5
1550	f	Activo	2017-03-22 08:50:24.77	\N	1	80	5
1551	f	Activo	2017-03-22 08:50:24.77	\N	2	80	5
1552	f	Activo	2017-03-22 08:50:24.77	\N	3	80	5
1553	f	Activo	2017-03-22 08:50:24.77	\N	4	80	5
1554	f	Activo	2017-03-22 08:50:24.77	\N	6	80	5
1555	f	Activo	2017-03-22 08:50:24.77	\N	1	82	5
1556	f	Activo	2017-03-22 08:50:24.77	\N	2	82	5
1557	f	Activo	2017-03-22 08:50:24.77	\N	3	82	5
1558	f	Activo	2017-03-22 08:50:24.77	\N	4	82	5
1559	f	Activo	2017-03-22 08:50:24.77	\N	6	82	5
1560	f	Activo	2017-03-22 08:50:24.77	\N	1	85	5
1561	f	Activo	2017-03-22 08:50:24.77	\N	2	85	5
1562	f	Activo	2017-03-22 08:50:24.77	\N	3	85	5
1563	f	Activo	2017-03-22 08:50:24.77	\N	4	85	5
1564	f	Activo	2017-03-22 08:50:24.77	\N	6	85	5
1565	f	Activo	2017-03-22 08:50:24.77	\N	1	86	5
1566	f	Activo	2017-03-22 08:50:24.77	\N	2	86	5
1567	f	Activo	2017-03-22 08:50:24.77	\N	3	86	5
1568	f	Activo	2017-03-22 08:50:24.77	\N	4	86	5
1569	f	Activo	2017-03-22 08:50:24.77	\N	6	86	5
1570	f	Activo	2017-03-22 08:50:24.77	\N	1	87	5
1571	f	Activo	2017-03-22 08:50:24.77	\N	2	87	5
1572	f	Activo	2017-03-22 08:50:24.77	\N	3	87	5
1573	f	Activo	2017-03-22 08:50:24.77	\N	4	87	5
1574	f	Activo	2017-03-22 08:50:24.77	\N	6	87	5
1575	f	Activo	2017-03-22 08:50:24.77	\N	1	88	5
1576	f	Activo	2017-03-22 08:50:24.77	\N	2	88	5
1577	f	Activo	2017-03-22 08:50:24.77	\N	3	88	5
1578	f	Activo	2017-03-22 08:50:24.77	\N	4	88	5
1579	f	Activo	2017-03-22 08:50:24.77	\N	6	88	5
1580	f	Activo	2017-03-22 08:50:24.77	\N	1	89	5
1581	f	Activo	2017-03-22 08:50:24.77	\N	2	89	5
1582	f	Activo	2017-03-22 08:50:24.77	\N	3	89	5
1583	f	Activo	2017-03-22 08:50:24.77	\N	4	89	5
1584	f	Activo	2017-03-22 08:50:24.77	\N	6	89	5
1585	f	Activo	2017-03-22 08:50:24.77	\N	1	90	5
1586	f	Activo	2017-03-22 08:50:24.77	\N	2	90	5
1587	f	Activo	2017-03-22 08:50:24.77	\N	3	90	5
1588	f	Activo	2017-03-22 08:50:24.77	\N	4	90	5
1589	f	Activo	2017-03-22 08:50:24.77	\N	6	90	5
1590	f	Activo	2017-03-22 08:50:24.77	\N	1	91	5
1591	f	Activo	2017-03-22 08:50:24.77	\N	2	91	5
1592	f	Activo	2017-03-22 08:50:24.77	\N	3	91	5
1593	f	Activo	2017-03-22 08:50:24.77	\N	4	91	5
1594	f	Activo	2017-03-22 08:50:24.77	\N	6	91	5
1595	f	Activo	2017-03-22 08:50:24.77	\N	1	92	5
1596	f	Activo	2017-03-22 08:50:24.77	\N	2	92	5
1597	f	Activo	2017-03-22 08:50:24.77	\N	3	92	5
1598	f	Activo	2017-03-22 08:50:24.77	\N	4	92	5
1599	f	Activo	2017-03-22 08:50:24.77	\N	6	92	5
1600	f	Activo	2017-03-22 08:50:24.77	\N	1	94	5
1601	f	Activo	2017-03-22 08:50:24.77	\N	2	94	5
1602	f	Activo	2017-03-22 08:50:24.77	\N	3	94	5
1603	f	Activo	2017-03-22 08:50:24.77	\N	4	94	5
1604	f	Activo	2017-03-22 08:50:24.77	\N	6	94	5
1605	f	Activo	2017-03-22 08:50:24.77	\N	1	96	5
1606	f	Activo	2017-03-22 08:50:24.77	\N	2	96	5
1607	f	Activo	2017-03-22 08:50:24.77	\N	3	96	5
1608	f	Activo	2017-03-22 08:50:24.77	\N	4	96	5
1609	f	Activo	2017-03-22 08:50:24.77	\N	6	96	5
1610	f	Activo	2017-03-22 08:50:24.77	\N	1	97	5
1611	f	Activo	2017-03-22 08:50:24.77	\N	2	97	5
1612	f	Activo	2017-03-22 08:50:24.77	\N	3	97	5
1613	f	Activo	2017-03-22 08:50:24.77	\N	4	97	5
1614	f	Activo	2017-03-22 08:50:24.77	\N	6	97	5
1615	f	Activo	2017-03-22 08:50:24.77	\N	1	99	5
1616	f	Activo	2017-03-22 08:50:24.77	\N	2	99	5
1617	f	Activo	2017-03-22 08:50:24.77	\N	3	99	5
1618	f	Activo	2017-03-22 08:50:24.77	\N	4	99	5
1619	f	Activo	2017-03-22 08:50:24.77	\N	6	99	5
1620	f	Activo	2017-03-22 08:50:24.77	\N	1	100	5
1621	f	Activo	2017-03-22 08:50:24.77	\N	2	100	5
1622	f	Activo	2017-03-22 08:50:24.77	\N	3	100	5
1623	f	Activo	2017-03-22 08:50:24.77	\N	4	100	5
1624	f	Activo	2017-03-22 08:50:24.77	\N	6	100	5
1625	f	Activo	2017-03-22 08:50:24.77	\N	1	101	5
1626	f	Activo	2017-03-22 08:50:24.77	\N	2	101	5
1627	f	Activo	2017-03-22 08:50:24.77	\N	3	101	5
1628	f	Activo	2017-03-22 08:50:24.77	\N	4	101	5
1629	f	Activo	2017-03-22 08:50:24.77	\N	6	101	5
1630	f	Activo	2017-03-22 08:50:24.858	\N	5	4	5
1631	f	Activo	2017-03-22 08:50:24.858	\N	5	3	5
1632	f	Activo	2017-03-22 08:50:24.858	\N	5	2	5
1633	f	Activo	2017-03-22 08:50:24.858	\N	5	14	5
1634	f	Activo	2017-03-22 08:50:24.858	\N	5	38	5
1635	f	Activo	2017-03-22 08:50:24.858	\N	5	40	5
1636	f	Activo	2017-03-22 08:50:24.858	\N	5	44	5
1637	f	Activo	2017-03-22 08:50:24.858	\N	5	48	5
1638	f	Activo	2017-03-22 08:50:24.858	\N	5	54	5
1639	f	Activo	2017-03-22 08:50:24.858	\N	5	58	5
1640	f	Activo	2017-03-22 08:50:24.858	\N	5	57	5
1641	f	Activo	2017-03-22 08:50:24.858	\N	5	61	5
1642	f	Activo	2017-03-22 08:50:24.858	\N	5	65	5
1643	f	Activo	2017-03-22 08:50:24.858	\N	5	64	5
1644	f	Activo	2017-03-22 08:50:24.858	\N	5	67	5
1645	f	Activo	2017-03-22 08:50:24.858	\N	5	70	5
1646	f	Activo	2017-03-22 08:50:24.858	\N	5	78	5
1647	f	Activo	2017-03-22 08:50:24.858	\N	5	81	5
1648	f	Activo	2017-03-22 08:50:24.858	\N	5	84	5
1649	f	Activo	2017-03-22 08:50:24.858	\N	5	83	5
1650	f	Activo	2017-03-22 08:50:24.858	\N	5	93	5
1651	f	Activo	2017-03-22 08:50:24.858	\N	5	95	5
1652	f	Activo	2017-03-22 08:50:24.858	\N	5	98	5
1653	f	Activo	2017-03-22 08:50:30.368	\N	1	1	6
1654	f	Activo	2017-03-22 08:50:30.368	\N	2	1	6
1655	f	Activo	2017-03-22 08:50:30.368	\N	3	1	6
1656	f	Activo	2017-03-22 08:50:30.368	\N	4	1	6
1657	f	Activo	2017-03-22 08:50:30.368	\N	6	1	6
1658	f	Activo	2017-03-22 08:50:30.368	\N	1	5	6
1659	f	Activo	2017-03-22 08:50:30.368	\N	2	5	6
1660	f	Activo	2017-03-22 08:50:30.368	\N	3	5	6
1661	f	Activo	2017-03-22 08:50:30.368	\N	4	5	6
1662	f	Activo	2017-03-22 08:50:30.368	\N	6	5	6
1663	f	Activo	2017-03-22 08:50:30.368	\N	1	6	6
1664	f	Activo	2017-03-22 08:50:30.368	\N	2	6	6
1665	f	Activo	2017-03-22 08:50:30.368	\N	3	6	6
1666	f	Activo	2017-03-22 08:50:30.368	\N	4	6	6
1667	f	Activo	2017-03-22 08:50:30.368	\N	6	6	6
1668	f	Activo	2017-03-22 08:50:30.368	\N	1	7	6
1669	f	Activo	2017-03-22 08:50:30.368	\N	2	7	6
1670	f	Activo	2017-03-22 08:50:30.368	\N	3	7	6
1671	f	Activo	2017-03-22 08:50:30.368	\N	4	7	6
1672	f	Activo	2017-03-22 08:50:30.368	\N	6	7	6
1673	f	Activo	2017-03-22 08:50:30.368	\N	1	8	6
1674	f	Activo	2017-03-22 08:50:30.368	\N	2	8	6
1675	f	Activo	2017-03-22 08:50:30.368	\N	3	8	6
1676	f	Activo	2017-03-22 08:50:30.368	\N	4	8	6
1677	f	Activo	2017-03-22 08:50:30.368	\N	6	8	6
1678	f	Activo	2017-03-22 08:50:30.368	\N	1	9	6
1679	f	Activo	2017-03-22 08:50:30.368	\N	2	9	6
1680	f	Activo	2017-03-22 08:50:30.368	\N	3	9	6
1681	f	Activo	2017-03-22 08:50:30.368	\N	4	9	6
1682	f	Activo	2017-03-22 08:50:30.368	\N	6	9	6
1683	f	Activo	2017-03-22 08:50:30.368	\N	1	10	6
1684	f	Activo	2017-03-22 08:50:30.368	\N	2	10	6
1685	f	Activo	2017-03-22 08:50:30.368	\N	3	10	6
1686	f	Activo	2017-03-22 08:50:30.368	\N	4	10	6
1687	f	Activo	2017-03-22 08:50:30.368	\N	6	10	6
1688	f	Activo	2017-03-22 08:50:30.368	\N	1	11	6
1689	f	Activo	2017-03-22 08:50:30.368	\N	2	11	6
1690	f	Activo	2017-03-22 08:50:30.368	\N	3	11	6
1691	f	Activo	2017-03-22 08:50:30.368	\N	4	11	6
1692	f	Activo	2017-03-22 08:50:30.368	\N	6	11	6
1693	f	Activo	2017-03-22 08:50:30.368	\N	1	12	6
1694	f	Activo	2017-03-22 08:50:30.368	\N	2	12	6
1695	f	Activo	2017-03-22 08:50:30.368	\N	3	12	6
1696	f	Activo	2017-03-22 08:50:30.368	\N	4	12	6
1697	f	Activo	2017-03-22 08:50:30.368	\N	6	12	6
1698	f	Activo	2017-03-22 08:50:30.368	\N	1	13	6
1699	f	Activo	2017-03-22 08:50:30.368	\N	2	13	6
1700	f	Activo	2017-03-22 08:50:30.368	\N	3	13	6
1701	f	Activo	2017-03-22 08:50:30.368	\N	4	13	6
1702	f	Activo	2017-03-22 08:50:30.368	\N	6	13	6
1703	f	Activo	2017-03-22 08:50:30.368	\N	1	15	6
1704	f	Activo	2017-03-22 08:50:30.368	\N	2	15	6
1705	f	Activo	2017-03-22 08:50:30.368	\N	3	15	6
1706	f	Activo	2017-03-22 08:50:30.368	\N	4	15	6
1707	f	Activo	2017-03-22 08:50:30.368	\N	6	15	6
1708	f	Activo	2017-03-22 08:50:30.368	\N	1	16	6
1709	f	Activo	2017-03-22 08:50:30.368	\N	2	16	6
1710	f	Activo	2017-03-22 08:50:30.368	\N	3	16	6
1711	f	Activo	2017-03-22 08:50:30.368	\N	4	16	6
1712	f	Activo	2017-03-22 08:50:30.368	\N	6	16	6
1713	f	Activo	2017-03-22 08:50:30.368	\N	1	17	6
1714	f	Activo	2017-03-22 08:50:30.368	\N	2	17	6
1715	f	Activo	2017-03-22 08:50:30.368	\N	3	17	6
1716	f	Activo	2017-03-22 08:50:30.368	\N	4	17	6
1717	f	Activo	2017-03-22 08:50:30.368	\N	6	17	6
1718	f	Activo	2017-03-22 08:50:30.368	\N	1	18	6
1719	f	Activo	2017-03-22 08:50:30.368	\N	2	18	6
1720	f	Activo	2017-03-22 08:50:30.368	\N	3	18	6
1721	f	Activo	2017-03-22 08:50:30.368	\N	4	18	6
1722	f	Activo	2017-03-22 08:50:30.368	\N	6	18	6
1723	f	Activo	2017-03-22 08:50:30.368	\N	1	19	6
1724	f	Activo	2017-03-22 08:50:30.368	\N	2	19	6
1725	f	Activo	2017-03-22 08:50:30.368	\N	3	19	6
1726	f	Activo	2017-03-22 08:50:30.368	\N	4	19	6
1727	f	Activo	2017-03-22 08:50:30.368	\N	6	19	6
1728	f	Activo	2017-03-22 08:50:30.368	\N	1	20	6
1729	f	Activo	2017-03-22 08:50:30.368	\N	2	20	6
1730	f	Activo	2017-03-22 08:50:30.368	\N	3	20	6
1731	f	Activo	2017-03-22 08:50:30.368	\N	4	20	6
1732	f	Activo	2017-03-22 08:50:30.368	\N	6	20	6
1733	f	Activo	2017-03-22 08:50:30.368	\N	1	21	6
1734	f	Activo	2017-03-22 08:50:30.368	\N	2	21	6
1735	f	Activo	2017-03-22 08:50:30.368	\N	3	21	6
1736	f	Activo	2017-03-22 08:50:30.368	\N	4	21	6
1737	f	Activo	2017-03-22 08:50:30.368	\N	6	21	6
1738	f	Activo	2017-03-22 08:50:30.368	\N	1	22	6
1739	f	Activo	2017-03-22 08:50:30.368	\N	2	22	6
1740	f	Activo	2017-03-22 08:50:30.368	\N	3	22	6
1741	f	Activo	2017-03-22 08:50:30.368	\N	4	22	6
1742	f	Activo	2017-03-22 08:50:30.368	\N	6	22	6
1743	f	Activo	2017-03-22 08:50:30.368	\N	1	23	6
1744	f	Activo	2017-03-22 08:50:30.368	\N	2	23	6
1745	f	Activo	2017-03-22 08:50:30.368	\N	3	23	6
1746	f	Activo	2017-03-22 08:50:30.368	\N	4	23	6
1747	f	Activo	2017-03-22 08:50:30.368	\N	6	23	6
1748	f	Activo	2017-03-22 08:50:30.368	\N	1	24	6
1749	f	Activo	2017-03-22 08:50:30.368	\N	2	24	6
1750	f	Activo	2017-03-22 08:50:30.368	\N	3	24	6
1751	f	Activo	2017-03-22 08:50:30.368	\N	4	24	6
1752	f	Activo	2017-03-22 08:50:30.368	\N	6	24	6
1753	f	Activo	2017-03-22 08:50:30.368	\N	1	25	6
1754	f	Activo	2017-03-22 08:50:30.368	\N	2	25	6
1755	f	Activo	2017-03-22 08:50:30.368	\N	3	25	6
1756	f	Activo	2017-03-22 08:50:30.368	\N	4	25	6
1757	f	Activo	2017-03-22 08:50:30.368	\N	6	25	6
1758	f	Activo	2017-03-22 08:50:30.368	\N	1	26	6
1759	f	Activo	2017-03-22 08:50:30.368	\N	2	26	6
1760	f	Activo	2017-03-22 08:50:30.368	\N	3	26	6
1761	f	Activo	2017-03-22 08:50:30.368	\N	4	26	6
1762	f	Activo	2017-03-22 08:50:30.368	\N	6	26	6
1763	f	Activo	2017-03-22 08:50:30.368	\N	1	27	6
1764	f	Activo	2017-03-22 08:50:30.368	\N	2	27	6
1765	f	Activo	2017-03-22 08:50:30.368	\N	3	27	6
1766	f	Activo	2017-03-22 08:50:30.368	\N	4	27	6
1767	f	Activo	2017-03-22 08:50:30.368	\N	6	27	6
1768	f	Activo	2017-03-22 08:50:30.368	\N	1	28	6
1769	f	Activo	2017-03-22 08:50:30.368	\N	2	28	6
1770	f	Activo	2017-03-22 08:50:30.368	\N	3	28	6
1771	f	Activo	2017-03-22 08:50:30.368	\N	4	28	6
1772	f	Activo	2017-03-22 08:50:30.368	\N	6	28	6
1773	f	Activo	2017-03-22 08:50:30.368	\N	1	29	6
1774	f	Activo	2017-03-22 08:50:30.368	\N	2	29	6
1775	f	Activo	2017-03-22 08:50:30.368	\N	3	29	6
1776	f	Activo	2017-03-22 08:50:30.368	\N	4	29	6
1777	f	Activo	2017-03-22 08:50:30.368	\N	6	29	6
1778	f	Activo	2017-03-22 08:50:30.368	\N	1	30	6
1779	f	Activo	2017-03-22 08:50:30.368	\N	2	30	6
1780	f	Activo	2017-03-22 08:50:30.368	\N	3	30	6
1781	f	Activo	2017-03-22 08:50:30.368	\N	4	30	6
1782	f	Activo	2017-03-22 08:50:30.368	\N	6	30	6
1783	f	Activo	2017-03-22 08:50:30.368	\N	1	31	6
1784	f	Activo	2017-03-22 08:50:30.368	\N	2	31	6
1785	f	Activo	2017-03-22 08:50:30.368	\N	3	31	6
1786	f	Activo	2017-03-22 08:50:30.368	\N	4	31	6
1787	f	Activo	2017-03-22 08:50:30.368	\N	6	31	6
1788	f	Activo	2017-03-22 08:50:30.368	\N	1	32	6
1789	f	Activo	2017-03-22 08:50:30.368	\N	2	32	6
1790	f	Activo	2017-03-22 08:50:30.368	\N	3	32	6
1791	f	Activo	2017-03-22 08:50:30.368	\N	4	32	6
1792	f	Activo	2017-03-22 08:50:30.368	\N	6	32	6
1793	f	Activo	2017-03-22 08:50:30.368	\N	1	33	6
1794	f	Activo	2017-03-22 08:50:30.368	\N	2	33	6
1795	f	Activo	2017-03-22 08:50:30.368	\N	3	33	6
1796	f	Activo	2017-03-22 08:50:30.368	\N	4	33	6
1797	f	Activo	2017-03-22 08:50:30.368	\N	6	33	6
1798	f	Activo	2017-03-22 08:50:30.368	\N	1	34	6
1799	f	Activo	2017-03-22 08:50:30.368	\N	2	34	6
1800	f	Activo	2017-03-22 08:50:30.368	\N	3	34	6
1801	f	Activo	2017-03-22 08:50:30.368	\N	4	34	6
1802	f	Activo	2017-03-22 08:50:30.368	\N	6	34	6
1803	f	Activo	2017-03-22 08:50:30.368	\N	1	35	6
1804	f	Activo	2017-03-22 08:50:30.368	\N	2	35	6
1805	f	Activo	2017-03-22 08:50:30.368	\N	3	35	6
1806	f	Activo	2017-03-22 08:50:30.368	\N	4	35	6
1807	f	Activo	2017-03-22 08:50:30.368	\N	6	35	6
1808	f	Activo	2017-03-22 08:50:30.368	\N	1	36	6
1809	f	Activo	2017-03-22 08:50:30.368	\N	2	36	6
1810	f	Activo	2017-03-22 08:50:30.368	\N	3	36	6
1811	f	Activo	2017-03-22 08:50:30.368	\N	4	36	6
1812	f	Activo	2017-03-22 08:50:30.368	\N	6	36	6
1813	f	Activo	2017-03-22 08:50:30.368	\N	1	37	6
1814	f	Activo	2017-03-22 08:50:30.368	\N	2	37	6
1815	f	Activo	2017-03-22 08:50:30.368	\N	3	37	6
1816	f	Activo	2017-03-22 08:50:30.368	\N	4	37	6
1817	f	Activo	2017-03-22 08:50:30.368	\N	6	37	6
1818	f	Activo	2017-03-22 08:50:30.368	\N	1	39	6
1819	f	Activo	2017-03-22 08:50:30.368	\N	2	39	6
1820	f	Activo	2017-03-22 08:50:30.368	\N	3	39	6
1821	f	Activo	2017-03-22 08:50:30.368	\N	4	39	6
1822	f	Activo	2017-03-22 08:50:30.368	\N	6	39	6
1823	f	Activo	2017-03-22 08:50:30.368	\N	1	41	6
1824	f	Activo	2017-03-22 08:50:30.368	\N	2	41	6
1825	f	Activo	2017-03-22 08:50:30.368	\N	3	41	6
1826	f	Activo	2017-03-22 08:50:30.368	\N	4	41	6
1827	f	Activo	2017-03-22 08:50:30.368	\N	6	41	6
1828	f	Activo	2017-03-22 08:50:30.368	\N	1	42	6
1829	f	Activo	2017-03-22 08:50:30.368	\N	2	42	6
1830	f	Activo	2017-03-22 08:50:30.368	\N	3	42	6
1831	f	Activo	2017-03-22 08:50:30.368	\N	4	42	6
1832	f	Activo	2017-03-22 08:50:30.368	\N	6	42	6
1833	f	Activo	2017-03-22 08:50:30.368	\N	1	43	6
1834	f	Activo	2017-03-22 08:50:30.368	\N	2	43	6
1835	f	Activo	2017-03-22 08:50:30.368	\N	3	43	6
1836	f	Activo	2017-03-22 08:50:30.368	\N	4	43	6
1837	f	Activo	2017-03-22 08:50:30.368	\N	6	43	6
1838	f	Activo	2017-03-22 08:50:30.368	\N	1	45	6
1839	f	Activo	2017-03-22 08:50:30.368	\N	2	45	6
1840	f	Activo	2017-03-22 08:50:30.368	\N	3	45	6
1841	f	Activo	2017-03-22 08:50:30.368	\N	4	45	6
1842	f	Activo	2017-03-22 08:50:30.368	\N	6	45	6
1843	f	Activo	2017-03-22 08:50:30.368	\N	1	46	6
1844	f	Activo	2017-03-22 08:50:30.368	\N	2	46	6
1845	f	Activo	2017-03-22 08:50:30.368	\N	3	46	6
1846	f	Activo	2017-03-22 08:50:30.368	\N	4	46	6
1847	f	Activo	2017-03-22 08:50:30.368	\N	6	46	6
1848	f	Activo	2017-03-22 08:50:30.368	\N	1	47	6
1849	f	Activo	2017-03-22 08:50:30.368	\N	2	47	6
1850	f	Activo	2017-03-22 08:50:30.368	\N	3	47	6
1851	f	Activo	2017-03-22 08:50:30.368	\N	4	47	6
1852	f	Activo	2017-03-22 08:50:30.368	\N	6	47	6
1853	f	Activo	2017-03-22 08:50:30.368	\N	1	49	6
1854	f	Activo	2017-03-22 08:50:30.368	\N	2	49	6
1855	f	Activo	2017-03-22 08:50:30.368	\N	3	49	6
1856	f	Activo	2017-03-22 08:50:30.368	\N	4	49	6
1857	f	Activo	2017-03-22 08:50:30.368	\N	6	49	6
1858	f	Activo	2017-03-22 08:50:30.368	\N	1	50	6
1859	f	Activo	2017-03-22 08:50:30.368	\N	2	50	6
1860	f	Activo	2017-03-22 08:50:30.368	\N	3	50	6
1861	f	Activo	2017-03-22 08:50:30.368	\N	4	50	6
1862	f	Activo	2017-03-22 08:50:30.368	\N	6	50	6
1863	f	Activo	2017-03-22 08:50:30.368	\N	1	51	6
1864	f	Activo	2017-03-22 08:50:30.368	\N	2	51	6
1865	f	Activo	2017-03-22 08:50:30.368	\N	3	51	6
1866	f	Activo	2017-03-22 08:50:30.368	\N	4	51	6
1867	f	Activo	2017-03-22 08:50:30.368	\N	6	51	6
1868	f	Activo	2017-03-22 08:50:30.368	\N	1	52	6
1869	f	Activo	2017-03-22 08:50:30.368	\N	2	52	6
1870	f	Activo	2017-03-22 08:50:30.368	\N	3	52	6
1871	f	Activo	2017-03-22 08:50:30.368	\N	4	52	6
1872	f	Activo	2017-03-22 08:50:30.368	\N	6	52	6
1873	f	Activo	2017-03-22 08:50:30.368	\N	1	53	6
1874	f	Activo	2017-03-22 08:50:30.368	\N	2	53	6
1875	f	Activo	2017-03-22 08:50:30.368	\N	3	53	6
1876	f	Activo	2017-03-22 08:50:30.368	\N	4	53	6
1877	f	Activo	2017-03-22 08:50:30.368	\N	6	53	6
1878	f	Activo	2017-03-22 08:50:30.368	\N	1	55	6
1879	f	Activo	2017-03-22 08:50:30.368	\N	2	55	6
1880	f	Activo	2017-03-22 08:50:30.368	\N	3	55	6
1881	f	Activo	2017-03-22 08:50:30.368	\N	4	55	6
1882	f	Activo	2017-03-22 08:50:30.368	\N	6	55	6
1883	f	Activo	2017-03-22 08:50:30.368	\N	1	56	6
1884	f	Activo	2017-03-22 08:50:30.368	\N	2	56	6
1885	f	Activo	2017-03-22 08:50:30.368	\N	3	56	6
1886	f	Activo	2017-03-22 08:50:30.368	\N	4	56	6
1887	f	Activo	2017-03-22 08:50:30.368	\N	6	56	6
1888	f	Activo	2017-03-22 08:50:30.368	\N	1	59	6
1889	f	Activo	2017-03-22 08:50:30.368	\N	2	59	6
1890	f	Activo	2017-03-22 08:50:30.368	\N	3	59	6
1891	f	Activo	2017-03-22 08:50:30.368	\N	4	59	6
1892	f	Activo	2017-03-22 08:50:30.368	\N	6	59	6
1893	f	Activo	2017-03-22 08:50:30.368	\N	1	60	6
1894	f	Activo	2017-03-22 08:50:30.368	\N	2	60	6
1895	f	Activo	2017-03-22 08:50:30.368	\N	3	60	6
1896	f	Activo	2017-03-22 08:50:30.368	\N	4	60	6
1897	f	Activo	2017-03-22 08:50:30.368	\N	6	60	6
1898	f	Activo	2017-03-22 08:50:30.368	\N	1	62	6
1899	f	Activo	2017-03-22 08:50:30.368	\N	2	62	6
1900	f	Activo	2017-03-22 08:50:30.368	\N	3	62	6
1901	f	Activo	2017-03-22 08:50:30.368	\N	4	62	6
1902	f	Activo	2017-03-22 08:50:30.368	\N	6	62	6
1903	f	Activo	2017-03-22 08:50:30.368	\N	1	63	6
1904	f	Activo	2017-03-22 08:50:30.368	\N	2	63	6
1905	f	Activo	2017-03-22 08:50:30.368	\N	3	63	6
1906	f	Activo	2017-03-22 08:50:30.368	\N	4	63	6
1907	f	Activo	2017-03-22 08:50:30.368	\N	6	63	6
1908	f	Activo	2017-03-22 08:50:30.368	\N	1	66	6
1909	f	Activo	2017-03-22 08:50:30.368	\N	2	66	6
1910	f	Activo	2017-03-22 08:50:30.368	\N	3	66	6
1911	f	Activo	2017-03-22 08:50:30.368	\N	4	66	6
1912	f	Activo	2017-03-22 08:50:30.368	\N	6	66	6
1913	f	Activo	2017-03-22 08:50:30.368	\N	1	68	6
1914	f	Activo	2017-03-22 08:50:30.368	\N	2	68	6
1915	f	Activo	2017-03-22 08:50:30.368	\N	3	68	6
1916	f	Activo	2017-03-22 08:50:30.368	\N	4	68	6
1917	f	Activo	2017-03-22 08:50:30.368	\N	6	68	6
1918	f	Activo	2017-03-22 08:50:30.368	\N	1	69	6
1919	f	Activo	2017-03-22 08:50:30.368	\N	2	69	6
1920	f	Activo	2017-03-22 08:50:30.368	\N	3	69	6
1921	f	Activo	2017-03-22 08:50:30.368	\N	4	69	6
1922	f	Activo	2017-03-22 08:50:30.368	\N	6	69	6
1923	f	Activo	2017-03-22 08:50:30.368	\N	1	71	6
1924	f	Activo	2017-03-22 08:50:30.368	\N	2	71	6
1925	f	Activo	2017-03-22 08:50:30.368	\N	3	71	6
1926	f	Activo	2017-03-22 08:50:30.368	\N	4	71	6
1927	f	Activo	2017-03-22 08:50:30.368	\N	6	71	6
1928	f	Activo	2017-03-22 08:50:30.368	\N	1	72	6
1929	f	Activo	2017-03-22 08:50:30.368	\N	2	72	6
1930	f	Activo	2017-03-22 08:50:30.368	\N	3	72	6
1931	f	Activo	2017-03-22 08:50:30.368	\N	4	72	6
1932	f	Activo	2017-03-22 08:50:30.368	\N	6	72	6
1933	f	Activo	2017-03-22 08:50:30.368	\N	1	73	6
1934	f	Activo	2017-03-22 08:50:30.368	\N	2	73	6
1935	f	Activo	2017-03-22 08:50:30.368	\N	3	73	6
1936	f	Activo	2017-03-22 08:50:30.368	\N	4	73	6
1937	f	Activo	2017-03-22 08:50:30.368	\N	6	73	6
1938	f	Activo	2017-03-22 08:50:30.368	\N	1	74	6
1939	f	Activo	2017-03-22 08:50:30.368	\N	2	74	6
1940	f	Activo	2017-03-22 08:50:30.368	\N	3	74	6
1941	f	Activo	2017-03-22 08:50:30.368	\N	4	74	6
1942	f	Activo	2017-03-22 08:50:30.368	\N	6	74	6
1943	f	Activo	2017-03-22 08:50:30.368	\N	1	75	6
1944	f	Activo	2017-03-22 08:50:30.368	\N	2	75	6
1945	f	Activo	2017-03-22 08:50:30.368	\N	3	75	6
1946	f	Activo	2017-03-22 08:50:30.368	\N	4	75	6
1947	f	Activo	2017-03-22 08:50:30.368	\N	6	75	6
1948	f	Activo	2017-03-22 08:50:30.368	\N	1	76	6
1949	f	Activo	2017-03-22 08:50:30.368	\N	2	76	6
1950	f	Activo	2017-03-22 08:50:30.368	\N	3	76	6
1951	f	Activo	2017-03-22 08:50:30.368	\N	4	76	6
1952	f	Activo	2017-03-22 08:50:30.368	\N	6	76	6
1953	f	Activo	2017-03-22 08:50:30.368	\N	1	77	6
1954	f	Activo	2017-03-22 08:50:30.368	\N	2	77	6
1955	f	Activo	2017-03-22 08:50:30.368	\N	3	77	6
1956	f	Activo	2017-03-22 08:50:30.368	\N	4	77	6
1957	f	Activo	2017-03-22 08:50:30.368	\N	6	77	6
1958	f	Activo	2017-03-22 08:50:30.368	\N	1	79	6
1959	f	Activo	2017-03-22 08:50:30.368	\N	2	79	6
1960	f	Activo	2017-03-22 08:50:30.368	\N	3	79	6
1961	f	Activo	2017-03-22 08:50:30.368	\N	4	79	6
1962	f	Activo	2017-03-22 08:50:30.368	\N	6	79	6
1963	f	Activo	2017-03-22 08:50:30.368	\N	1	80	6
1964	f	Activo	2017-03-22 08:50:30.368	\N	2	80	6
1965	f	Activo	2017-03-22 08:50:30.368	\N	3	80	6
1966	f	Activo	2017-03-22 08:50:30.368	\N	4	80	6
1967	f	Activo	2017-03-22 08:50:30.368	\N	6	80	6
1968	f	Activo	2017-03-22 08:50:30.368	\N	1	82	6
1969	f	Activo	2017-03-22 08:50:30.368	\N	2	82	6
1970	f	Activo	2017-03-22 08:50:30.368	\N	3	82	6
1971	f	Activo	2017-03-22 08:50:30.368	\N	4	82	6
1972	f	Activo	2017-03-22 08:50:30.368	\N	6	82	6
1973	f	Activo	2017-03-22 08:50:30.368	\N	1	85	6
1974	f	Activo	2017-03-22 08:50:30.368	\N	2	85	6
1975	f	Activo	2017-03-22 08:50:30.368	\N	3	85	6
1976	f	Activo	2017-03-22 08:50:30.368	\N	4	85	6
1977	f	Activo	2017-03-22 08:50:30.368	\N	6	85	6
1978	f	Activo	2017-03-22 08:50:30.368	\N	1	86	6
1979	f	Activo	2017-03-22 08:50:30.368	\N	2	86	6
1980	f	Activo	2017-03-22 08:50:30.368	\N	3	86	6
1981	f	Activo	2017-03-22 08:50:30.368	\N	4	86	6
1982	f	Activo	2017-03-22 08:50:30.368	\N	6	86	6
1983	f	Activo	2017-03-22 08:50:30.368	\N	1	87	6
1984	f	Activo	2017-03-22 08:50:30.368	\N	2	87	6
1985	f	Activo	2017-03-22 08:50:30.368	\N	3	87	6
1986	f	Activo	2017-03-22 08:50:30.368	\N	4	87	6
1987	f	Activo	2017-03-22 08:50:30.368	\N	6	87	6
1988	f	Activo	2017-03-22 08:50:30.368	\N	1	88	6
1989	f	Activo	2017-03-22 08:50:30.368	\N	2	88	6
1990	f	Activo	2017-03-22 08:50:30.368	\N	3	88	6
1991	f	Activo	2017-03-22 08:50:30.368	\N	4	88	6
1992	f	Activo	2017-03-22 08:50:30.368	\N	6	88	6
1993	f	Activo	2017-03-22 08:50:30.368	\N	1	89	6
1994	f	Activo	2017-03-22 08:50:30.368	\N	2	89	6
1995	f	Activo	2017-03-22 08:50:30.368	\N	3	89	6
1996	f	Activo	2017-03-22 08:50:30.368	\N	4	89	6
1997	f	Activo	2017-03-22 08:50:30.368	\N	6	89	6
1998	f	Activo	2017-03-22 08:50:30.368	\N	1	90	6
1999	f	Activo	2017-03-22 08:50:30.368	\N	2	90	6
2000	f	Activo	2017-03-22 08:50:30.368	\N	3	90	6
2001	f	Activo	2017-03-22 08:50:30.368	\N	4	90	6
2002	f	Activo	2017-03-22 08:50:30.368	\N	6	90	6
2003	f	Activo	2017-03-22 08:50:30.368	\N	1	91	6
2004	f	Activo	2017-03-22 08:50:30.368	\N	2	91	6
2005	f	Activo	2017-03-22 08:50:30.368	\N	3	91	6
2006	f	Activo	2017-03-22 08:50:30.368	\N	4	91	6
2007	f	Activo	2017-03-22 08:50:30.368	\N	6	91	6
2008	f	Activo	2017-03-22 08:50:30.368	\N	1	92	6
2009	f	Activo	2017-03-22 08:50:30.368	\N	2	92	6
2010	f	Activo	2017-03-22 08:50:30.368	\N	3	92	6
2011	f	Activo	2017-03-22 08:50:30.368	\N	4	92	6
2012	f	Activo	2017-03-22 08:50:30.368	\N	6	92	6
2013	f	Activo	2017-03-22 08:50:30.368	\N	1	94	6
2014	f	Activo	2017-03-22 08:50:30.368	\N	2	94	6
2015	f	Activo	2017-03-22 08:50:30.368	\N	3	94	6
2016	f	Activo	2017-03-22 08:50:30.368	\N	4	94	6
2017	f	Activo	2017-03-22 08:50:30.368	\N	6	94	6
2018	f	Activo	2017-03-22 08:50:30.368	\N	1	96	6
2019	f	Activo	2017-03-22 08:50:30.368	\N	2	96	6
2020	f	Activo	2017-03-22 08:50:30.368	\N	3	96	6
2021	f	Activo	2017-03-22 08:50:30.368	\N	4	96	6
2022	f	Activo	2017-03-22 08:50:30.368	\N	6	96	6
2023	f	Activo	2017-03-22 08:50:30.368	\N	1	97	6
2024	f	Activo	2017-03-22 08:50:30.368	\N	2	97	6
2025	f	Activo	2017-03-22 08:50:30.368	\N	3	97	6
2026	f	Activo	2017-03-22 08:50:30.368	\N	4	97	6
2027	f	Activo	2017-03-22 08:50:30.368	\N	6	97	6
2028	f	Activo	2017-03-22 08:50:30.368	\N	1	99	6
2029	f	Activo	2017-03-22 08:50:30.368	\N	2	99	6
2030	f	Activo	2017-03-22 08:50:30.368	\N	3	99	6
2031	f	Activo	2017-03-22 08:50:30.368	\N	4	99	6
2032	f	Activo	2017-03-22 08:50:30.368	\N	6	99	6
2033	f	Activo	2017-03-22 08:50:30.368	\N	1	100	6
2034	f	Activo	2017-03-22 08:50:30.368	\N	2	100	6
2035	f	Activo	2017-03-22 08:50:30.368	\N	3	100	6
2036	f	Activo	2017-03-22 08:50:30.368	\N	4	100	6
2037	f	Activo	2017-03-22 08:50:30.368	\N	6	100	6
2038	f	Activo	2017-03-22 08:50:30.368	\N	1	101	6
2039	f	Activo	2017-03-22 08:50:30.368	\N	2	101	6
2040	f	Activo	2017-03-22 08:50:30.368	\N	3	101	6
2041	f	Activo	2017-03-22 08:50:30.368	\N	4	101	6
2042	f	Activo	2017-03-22 08:50:30.368	\N	6	101	6
2043	f	Activo	2017-03-22 08:50:30.49	\N	5	4	6
2044	f	Activo	2017-03-22 08:50:30.49	\N	5	3	6
2045	f	Activo	2017-03-22 08:50:30.49	\N	5	2	6
2046	f	Activo	2017-03-22 08:50:30.49	\N	5	14	6
2047	f	Activo	2017-03-22 08:50:30.49	\N	5	38	6
2048	f	Activo	2017-03-22 08:50:30.49	\N	5	40	6
2049	f	Activo	2017-03-22 08:50:30.49	\N	5	44	6
2050	f	Activo	2017-03-22 08:50:30.49	\N	5	48	6
2051	f	Activo	2017-03-22 08:50:30.49	\N	5	54	6
2052	f	Activo	2017-03-22 08:50:30.49	\N	5	58	6
2053	f	Activo	2017-03-22 08:50:30.49	\N	5	57	6
2054	f	Activo	2017-03-22 08:50:30.49	\N	5	61	6
2055	f	Activo	2017-03-22 08:50:30.49	\N	5	65	6
2056	f	Activo	2017-03-22 08:50:30.49	\N	5	64	6
2057	f	Activo	2017-03-22 08:50:30.49	\N	5	67	6
2058	f	Activo	2017-03-22 08:50:30.49	\N	5	70	6
2059	f	Activo	2017-03-22 08:50:30.49	\N	5	78	6
2060	f	Activo	2017-03-22 08:50:30.49	\N	5	81	6
2061	f	Activo	2017-03-22 08:50:30.49	\N	5	84	6
2062	f	Activo	2017-03-22 08:50:30.49	\N	5	83	6
2063	f	Activo	2017-03-22 08:50:30.49	\N	5	93	6
2064	f	Activo	2017-03-22 08:50:30.49	\N	5	95	6
2065	f	Activo	2017-03-22 08:50:30.49	\N	5	98	6
\.


--
-- TOC entry 3599 (class 0 OID 0)
-- Dependencies: 319
-- Name: permiso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('permiso_id_seq', 2065, true);


--
-- TOC entry 3359 (class 0 OID 96408)
-- Dependencies: 231
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persona (id, apellido, cedula, direccion, estatus, fechacreacion, fechamodificacion, fechanacimiento, nombre, sexo, telefono, urlfoto, iddeporte, idocupacion, idpasatiempo, idprofesion) FROM stdin;
1	ALDANA	V-1	Carrera 22 entre calles 12 y 13	Activo	2017-03-22 08:50:08.256	\N	2017-03-22 08:50:08.256	ANDY	Masculino	(0416)5017020	\N	\N	\N	\N	\N
2	RAMOS	V-2	Carrera 22 entre calles 12 y 13	Activo	2017-03-22 08:50:08.433	\N	2017-03-22 08:50:08.433	JOSE LUIS	Masculino	(0416)5017020	\N	\N	\N	\N	\N
3	LISCANO	V-3	Carrera 22 entre calles 12 y 13	Activo	2017-03-22 08:50:08.477	\N	2017-03-22 08:50:08.477	JOSE JAVIER	Masculino	(0416)5017020	\N	\N	\N	\N	\N
4	Piña	20926862	\N	Activo	2017-03-22 14:27:53.643941	\N	2017-03-22 14:27:53.643963	Jaiván	Por definir	Por definir	\N	\N	\N	\N	\N
5	Alvarado	5241902	\N	Activo	2017-03-22 14:30:36.964374	\N	2017-03-22 14:30:36.964391	María	Por definir	Por definir	\N	\N	\N	\N	\N
\.


--
-- TOC entry 3600 (class 0 OID 0)
-- Dependencies: 320
-- Name: persona_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('persona_id_seq', 3, true);


--
-- TOC entry 3360 (class 0 OID 96418)
-- Dependencies: 232
-- Data for Name: preguntafrecuente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY preguntafrecuente (id, estatus, fechacreacion, fechamodificacion, pregunta, respuesta) FROM stdin;
1	Activo	2017-03-17 19:41:25.013	2017-03-17 00:00:00	Al cambiar de marca de lubricante se daa el motor	Esta idea es totalmente falsa. Todos los lubricantes de marcas reconocidas cumplen con los requerimientos de desempeo del motor. Es muy importante que al cambiar de aceite o marca, el lubricante nuevo y el antiguo sean compatibles.
\.


--
-- TOC entry 3601 (class 0 OID 0)
-- Dependencies: 321
-- Name: preguntafrecuente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('preguntafrecuente_id_seq', 1, false);


--
-- TOC entry 3361 (class 0 OID 96426)
-- Dependencies: 233
-- Data for Name: presupuesto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY presupuesto (id, descripcion, estado, estatus, fechacreacion, fechamodificacion, monto_total, idcita) FROM stdin;
\.


--
-- TOC entry 3602 (class 0 OID 0)
-- Dependencies: 322
-- Name: presupuesto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('presupuesto_id_seq', 1, false);


--
-- TOC entry 3362 (class 0 OID 96434)
-- Dependencies: 234
-- Data for Name: presupuestoservicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY presupuestoservicio (id, estatus, fechacreacion, fechamodificacion, idpresupuesto, idservicio) FROM stdin;
\.


--
-- TOC entry 3603 (class 0 OID 0)
-- Dependencies: 323
-- Name: presupuestoservicio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('presupuestoservicio_id_seq', 1, false);


--
-- TOC entry 3363 (class 0 OID 96439)
-- Dependencies: 235
-- Data for Name: presupuestotiporepuesto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY presupuestotiporepuesto (id, estatus, fechacreacion, fechamodificacion, idpresupuesto, idtiporepuesto) FROM stdin;
\.


--
-- TOC entry 3604 (class 0 OID 0)
-- Dependencies: 324
-- Name: presupuestotiporepuesto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('presupuestotiporepuesto_id_seq', 1, false);


--
-- TOC entry 3364 (class 0 OID 96444)
-- Dependencies: 236
-- Data for Name: profesion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY profesion (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Ingeniero	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Contador	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Quimico	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Gerente	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Matematico	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Bachiller	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Otro	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	Medico Cirujano	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	fotografo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
10	Ingeniero Civil	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
11	Veterinario	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
12	Abogado	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3605 (class 0 OID 0)
-- Dependencies: 325
-- Name: profesion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('profesion_id_seq', 12, true);


--
-- TOC entry 3365 (class 0 OID 96454)
-- Dependencies: 237
-- Data for Name: promocion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY promocion (id, descripcion, descuento, estado, estatus, fechacreacion, fechamodificacion, fechavigenciafin, fechavigenciainicio, imagen, titulo, idservicio) FROM stdin;
1	Con marca de vehiculos Hyundai con un 5% de descuento sobre el precio del servicio	5	Por Asignar	Activo	2017-03-22 09:17:35.426	\N	2017-03-17 00:00:00	2017-03-16 00:00:00	/media/images/promociones/img_noticia2611855973279002306.jpeg	PROMOCION DE CAMBIO DE ACEITE DIRIGIDA PARA LOS CLIENTES	1
2	Cambio de Aceite para Vehiculos Chery	6	Por Asignar	Activo	2017-03-22 09:18:38.383	\N	2017-03-24 00:00:00	2017-03-10 00:00:00	/media/images/promociones/img_noticia8488577302614281186.jpeg	PROMOCION DE CAMBIO DE ACEITE DIRIGIDA PARA LOS 5 PRIMEROS CLIENTES CON UN 9% DE DESCUENTO SOBRE EL PRECIO DEL SERVICIO	7
3	Promocion de cambio de correa de distribución dirigida para los Clientes, con marca de vehiculos Toyota y Ford con un 8% de descuento sobre el precio del servicio	15	Por Asignar	Activo	2017-03-22 09:23:21.512	\N	2017-03-31 00:00:00	2017-03-24 00:00:00	/media/images/promociones/img_noticia3395904457497332855.jpeg	CAMBIO DE CORREA DE DISTRIBUCIÓN PARA VEHICULOS TOYOTA Y FORD	7
4	Promocion de cambio de correa de distribución dirigida para los 10 primeros Clientes que hagan un solicitud de cita, con un 10% de descuento sobre el precio del servicio	5	Por Asignar	Activo	2017-03-22 09:27:17.041	\N	2017-03-31 00:00:00	2017-03-24 00:00:00	/media/images/promociones/img_noticia9140823999546389970.jpeg	CAMBIO DE CORREA DE DISTRIBUCIÓN VEHICULOS CHERY,JEEP, TOYOTA Y FORD	5
\.


--
-- TOC entry 3606 (class 0 OID 0)
-- Dependencies: 326
-- Name: promocion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('promocion_id_seq', 4, true);


--
-- TOC entry 3366 (class 0 OID 96464)
-- Dependencies: 238
-- Data for Name: prueba; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY prueba (id, descripcion, estado, estatus, fechacreacion, fechamodificacion, idordenservicio) FROM stdin;
\.


--
-- TOC entry 3607 (class 0 OID 0)
-- Dependencies: 327
-- Name: prueba_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('prueba_id_seq', 1, false);


--
-- TOC entry 3367 (class 0 OID 96472)
-- Dependencies: 239
-- Data for Name: reclamo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY reclamo (id, descripcion, estado, estatus, fechacreacion, fechamodificacion, idmotivo, idordenentrega, idtiporeclamo) FROM stdin;
\.


--
-- TOC entry 3608 (class 0 OID 0)
-- Dependencies: 328
-- Name: reclamo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('reclamo_id_seq', 1, false);


--
-- TOC entry 3368 (class 0 OID 96482)
-- Dependencies: 240
-- Data for Name: rectificacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY rectificacion (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	0.10	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	0.20	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	0.30	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3609 (class 0 OID 0)
-- Dependencies: 329
-- Name: rectificacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rectificacion_id_seq', 3, true);


--
-- TOC entry 3369 (class 0 OID 96492)
-- Dependencies: 241
-- Data for Name: redsocial; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY redsocial (id, estatus, fechacreacion, fechamodificacion, nombre, url, idsistema) FROM stdin;
1	Activo	\N	\N	Twitter	https://www.twitter.com/	1
2	Activo	\N	\N	Facebook	https://www.facebook.com/	1
3	Activo	\N	\N	Instagram	http://instagram.com/	1
4	Activo	\N	\N	Vimeo	http://vimeo.com/	1
5	Activo	\N	\N	G+	https://plus.google.com/	1
6	Activo	\N	\N	Pinterest	https://www.pinterest.com/	1
\.


--
-- TOC entry 3610 (class 0 OID 0)
-- Dependencies: 330
-- Name: redsocial_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('redsocial_id_seq', 1, false);


--
-- TOC entry 3370 (class 0 OID 96502)
-- Dependencies: 242
-- Data for Name: refrigerante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY refrigerante (id, descripcion, estatus, fechacreacion, fechamodificacion, idtiporefrigerante) FROM stdin;
1	Refrigerante Protector Antioxidante	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
2	Refrigerante Mopar Antioxidante	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
3	Refrigerante verde Anticorrosivo de Galn	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
4	Refrigerante Anticorrosivo Deldxe verde	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
5	Refrigerante  Anticorrosivo rojo ultracool-ultralub	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
6	Refrigerante turbo Tx essential	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
7	Refrigerante tosaka 50-50 rojo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
8	Refrigerante tempertone verde antioxidante	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
9	Refrigerante speed cool	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	1
10	Autotek W1010	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	2
11	Ac Delco	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	2
12	Prestone Dex-cool	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	2
13	Repsol	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	2
14	Sin minerales	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	3
15	Vistony	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	4
16	Lubristone Coolant	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	4
\.


--
-- TOC entry 3611 (class 0 OID 0)
-- Dependencies: 331
-- Name: refrigerante_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('refrigerante_id_seq', 16, true);


--
-- TOC entry 3371 (class 0 OID 96510)
-- Dependencies: 243
-- Data for Name: servicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY servicio (id, descripcion, estado, estatus, fechacreacion, fechamodificacion, tarifa, titulo, urlimagen) FROM stdin;
1	Para mantener tu coche funcionando en las mejores condiciones, es importante que sepas cómo y cuándo cambiar el aceite a tu coche, ya que el aceite se deteriora con el tiempo y el filtro se bloquea con contaminantes.	Por Asignar	Activo	2017-03-22 09:06:45.543	\N	2500	CAMBIO DE ACEITE	/media/images/servicios/img_noticia1370675521219498457.jpeg
2	La correa de distribución del coche es la encargada de asegurar la sincronización entre la fase de admisión de la mezcla aire-carburante (árbol de levas) y el movimiento de las válvulas con la rotación del cigüeñal y el movimiento de los pistones.	Por Asignar	Activo	2017-03-22 09:07:55.045	\N	5000	CAMBIO DE CORREA	/media/images/servicios/img_noticia7276695940352816669.jpeg
3	Consiste en reparar el motor de un vehículo.	Por Asignar	Activo	2017-03-22 09:09:03.099	\N	1200	REPARACIÓN DE MOTOR	/media/images/servicios/img_noticia6954278983791648560.jpeg
4	El centro de balanceo de un vehículo es el punto imaginario en el que las fuerzas originadas en la suspensión al dar una curva son transmitidas al cuerpo del vehiculo	Por Asignar	Activo	2017-03-22 09:10:46.149	\N	8000	BALANCEO	/media/images/servicios/img_noticia4911329975513263058.jpeg
5	El tren delantero y la suspensión está compuesto por los siguiente componentes y piezas: ... Ofrecemos solo productos originales y adecuados a su vehículo.	Por Asignar	Activo	2017-03-22 09:11:57.218	\N	3000	TREN DELANTERO	/media/images/servicios/img_noticia4375702710406465412.jpeg
6	Tren delantero y balance	Por Asignar	Activo	2017-03-22 09:13:11.153	\N	2300	TREN DELANTERO Y BALANCE	/media/images/servicios/img_noticia3376784194005871289.jpeg
7	El amortiguador es un dispositivo construido con un eje cromado y dos tubos de acero (uno dentro del otro).	Por Asignar	Activo	2017-03-22 09:13:42.563	\N	4000	AMORIGUADOR	/media/images/servicios/img_noticia1118317969191376040.jpeg
8	Reparación de Base de Motor	Por Asignar	Activo	2017-03-22 09:14:15.716	\N	9000	BASE MOTOR	/media/images/servicios/img_noticia3919655639819008991.png
9	Reparación de tripoide	Por Asignar	Activo	2017-03-22 09:14:46.315	\N	3000	TRIPOIDE	/media/images/servicios/img_noticia8842289531952829638.jpeg
10	Escaneo computarizado	Por Asignar	Activo	2017-03-22 09:15:30.982	\N	3000	ESCANER	/media/images/servicios/img_noticia2194677264060871138.jpeg
\.


--
-- TOC entry 3612 (class 0 OID 0)
-- Dependencies: 332
-- Name: servicio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('servicio_id_seq', 10, true);


--
-- TOC entry 3613 (class 0 OID 0)
-- Dependencies: 333
-- Name: servicioetapa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('servicioetapa_id_seq', 1, false);


--
-- TOC entry 3614 (class 0 OID 0)
-- Dependencies: 334
-- Name: servicioherramienta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('servicioherramienta_id_seq', 1, false);


--
-- TOC entry 3372 (class 0 OID 96518)
-- Dependencies: 244
-- Data for Name: serviciosetapas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY serviciosetapas (id, estatus, fechacreacion, fechamodificacion, idetapa, idservicio) FROM stdin;
\.


--
-- TOC entry 3373 (class 0 OID 96523)
-- Dependencies: 245
-- Data for Name: serviciosherramientas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY serviciosherramientas (id, estatus, fechacreacion, fechamodificacion, idherramienta, idservicio) FROM stdin;
\.


--
-- TOC entry 3374 (class 0 OID 96528)
-- Dependencies: 246
-- Data for Name: serviciostecnologias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY serviciostecnologias (id, estatus, fechacreacion, fechamodificacion, idservicio, idtecnologia) FROM stdin;
\.


--
-- TOC entry 3615 (class 0 OID 0)
-- Dependencies: 335
-- Name: serviciotecnologia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('serviciotecnologia_id_seq', 1, false);


--
-- TOC entry 3375 (class 0 OID 96533)
-- Dependencies: 247
-- Data for Name: sesion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sesion (seseion_id, descripcion, ultima_sesion, usuario_id) FROM stdin;
\.


--
-- TOC entry 3376 (class 0 OID 96538)
-- Dependencies: 248
-- Data for Name: sistema; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sistema (id, celular, correo, descripcion, direccion, estatus, fechacreacion, fechamodificacion, filosofia, icono, mapa, mision, nombre, telefono, timelinetwitter, timelinetwittervisible, urlimagencabezera, urlimagencabezeravisible, vision) FROM stdin;
1	\N	prime@prime.com	SI	\N	Activo	2017-03-22 08:50:05.658	\N	\N	wwww	\N	\N	Prime	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 3616 (class 0 OID 0)
-- Dependencies: 336
-- Name: sistema_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sistema_id_seq', 1, true);


--
-- TOC entry 3377 (class 0 OID 96548)
-- Dependencies: 249
-- Data for Name: slider; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY slider (id, descripcion, estatus, fechacreacion, fechamodificacion, nombre, urlimagen) FROM stdin;
1	Descripción Slider 1	Activo	2017-03-22 09:29:50.6	2017-03-22 09:29:50.6	Slider 1	/media/images/slider/img_slider1932449747427352203.png
2	Descricpción Slider 2	Activo	2017-03-22 09:30:25.746	2017-03-22 09:30:25.746	Slider 2	/media/images/slider/img_slider8413936857538444339.png
3	Descripción Slider 3	Activo	2017-03-22 09:31:30.298	2017-03-22 09:31:30.298	Slider 3	/media/images/slider/img_slider8265597982093437432.png
\.


--
-- TOC entry 3617 (class 0 OID 0)
-- Dependencies: 337
-- Name: slider_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('slider_id_seq', 3, true);


--
-- TOC entry 3378 (class 0 OID 96558)
-- Dependencies: 250
-- Data for Name: tarifa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tarifa (id, descripcion, estatus, fechacreacion, fechamodificacion, monto) FROM stdin;
\.


--
-- TOC entry 3618 (class 0 OID 0)
-- Dependencies: 338
-- Name: tarifa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tarifa_id_seq', 1, false);


--
-- TOC entry 3379 (class 0 OID 96566)
-- Dependencies: 251
-- Data for Name: tecnologia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tecnologia (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	 Scanner Automotriz AUTEL - MAXISYS MINI	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	 Scanner Automotriz AUTEL MAXISYS MS906	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Scanner Automotriz GM MDI	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	 Scanner Automotriz STAR MOBILE	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Scanner Automotriz Tech 2 GM	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	 Scanner Automotriz CHRYSLER DRB III	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Scanner Diesel JALTEST	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	Scanner Diesel JhonDeere	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	Scanner Diesel Opacmetro Diesel OPA 495	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
10	 Scanner Diesel Jaltest Soft	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
11	Scanner Diesel Caterpillar Diagnose SWT	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
12	OSCILOSCOPIO AUTOMOTRIZ  4 CANALES  AUTEL - MAXISCOPE MP-408 	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
13	Maquina Limpeza de inyectotes TEKTINO INJ-6B	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
14	Maquina Limpeza de inyectotes LAUNCH CNC 402 A	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
15	Maquina Limpeza de inyectotesLaunch CNC 602 A	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3619 (class 0 OID 0)
-- Dependencies: 339
-- Name: tecnologia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tecnologia_id_seq', 15, true);


--
-- TOC entry 3516 (class 0 OID 97607)
-- Dependencies: 388
-- Data for Name: thumbnail_kvstore; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY thumbnail_kvstore (key, value) FROM stdin;
\.


--
-- TOC entry 3380 (class 0 OID 96574)
-- Dependencies: 252
-- Data for Name: tipoaceite; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipoaceite (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	sintetico	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	semi-sintetico	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	minerales	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3620 (class 0 OID 0)
-- Dependencies: 340
-- Name: tipoaceite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipoaceite_id_seq', 3, true);


--
-- TOC entry 3381 (class 0 OID 96584)
-- Dependencies: 253
-- Data for Name: tipocaja; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipocaja (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Automaticas	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Manuales	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Secuenciales	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3621 (class 0 OID 0)
-- Dependencies: 341
-- Name: tipocaja_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipocaja_id_seq', 3, true);


--
-- TOC entry 3382 (class 0 OID 96594)
-- Dependencies: 254
-- Data for Name: tipoclase; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipoclase (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Ambulancia	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Camioneta	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Deportivo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Convertible	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Fnebre	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Rustico	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Van	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
8	Familiar	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
9	De Carga	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
10	Cabina Doble	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
11	Cabina Extra	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
12	Cabina simple	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
13	Grua	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
14	Plataforma	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3622 (class 0 OID 0)
-- Dependencies: 342
-- Name: tipoclase_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipoclase_id_seq', 14, true);


--
-- TOC entry 3383 (class 0 OID 96604)
-- Dependencies: 255
-- Data for Name: tipocombustible; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipocombustible (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Sin plomo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Con plomo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Mediano	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Marino	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3623 (class 0 OID 0)
-- Dependencies: 343
-- Name: tipocombustible_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipocombustible_id_seq', 4, true);


--
-- TOC entry 3384 (class 0 OID 96614)
-- Dependencies: 256
-- Data for Name: tipoeventualidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipoeventualidad (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Por el Cliente	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00
2	Por el Taller	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3624 (class 0 OID 0)
-- Dependencies: 344
-- Name: tipoeventualidad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipoeventualidad_id_seq', 2, true);


--
-- TOC entry 3385 (class 0 OID 96624)
-- Dependencies: 257
-- Data for Name: tipogarantia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipogarantia (id, estatus, fechacreacion, fechamodificacion, nombre) FROM stdin;
1	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	Repuesto
2	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00	Mano de obra
\.


--
-- TOC entry 3625 (class 0 OID 0)
-- Dependencies: 345
-- Name: tipogarantia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipogarantia_id_seq', 2, true);


--
-- TOC entry 3386 (class 0 OID 96634)
-- Dependencies: 258
-- Data for Name: tipomotor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipomotor (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Motor de Gasolina	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Motor Disel	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Motor Elctrico	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3626 (class 0 OID 0)
-- Dependencies: 346
-- Name: tipomotor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipomotor_id_seq', 3, true);


--
-- TOC entry 3387 (class 0 OID 96644)
-- Dependencies: 259
-- Data for Name: tiponotificacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tiponotificacion (id, estatus, fechacreacion, fechamodificacion, icono, nombre) FROM stdin;
1	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	icono	Promocion
2	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	icono	Presupuesto
3	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	icono	Eventualidad
4	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	icono	Orden de Entrega
5	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00	icono	Calificacion
\.


--
-- TOC entry 3627 (class 0 OID 0)
-- Dependencies: 352
-- Name: tiponotificacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tiponotificacion_id_seq', 1, false);


--
-- TOC entry 3388 (class 0 OID 96652)
-- Dependencies: 260
-- Data for Name: tiporeclamo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tiporeclamo (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Reclamo por repuesto daado 	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00
2	Mano de obra inconclusa 	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00
3	Fallas que no presentaba antes de recibir el servicio 	Activo	2017-03-02 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3628 (class 0 OID 0)
-- Dependencies: 347
-- Name: tiporeclamo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tiporeclamo_id_seq', 3, true);


--
-- TOC entry 3389 (class 0 OID 96662)
-- Dependencies: 261
-- Data for Name: tiporefrigerante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tiporefrigerante (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Refrigerante Anticorrosivo	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Anticongelante	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Agua Destilada o Desmineralizada	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Agua Verde	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3629 (class 0 OID 0)
-- Dependencies: 348
-- Name: tiporefrigerante_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tiporefrigerante_id_seq', 4, true);


--
-- TOC entry 3390 (class 0 OID 96672)
-- Dependencies: 262
-- Data for Name: tiporepuesto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tiporepuesto (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
\.


--
-- TOC entry 3630 (class 0 OID 0)
-- Dependencies: 349
-- Name: tiporepuesto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tiporepuesto_id_seq', 1, false);


--
-- TOC entry 3391 (class 0 OID 96680)
-- Dependencies: 263
-- Data for Name: tiposervicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tiposervicio (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
\.


--
-- TOC entry 3631 (class 0 OID 0)
-- Dependencies: 350
-- Name: tiposervicio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tiposervicio_id_seq', 1, false);


--
-- TOC entry 3392 (class 0 OID 96688)
-- Dependencies: 264
-- Data for Name: tipotecnologia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipotecnologia (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Scanner Automotriz	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Scanner Diesel	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Osciloscopio Automotriz	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Maquina Limpeza de inyectotes	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3632 (class 0 OID 0)
-- Dependencies: 351
-- Name: tipotecnologia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipotecnologia_id_seq', 4, true);


--
-- TOC entry 3393 (class 0 OID 96696)
-- Dependencies: 265
-- Data for Name: uso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY uso (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Taxista	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Particular	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Transporte	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Escolta de Gandola	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3633 (class 0 OID 0)
-- Dependencies: 353
-- Name: uso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('uso_id_seq', 4, true);


--
-- TOC entry 3394 (class 0 OID 96706)
-- Dependencies: 266
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (id, contrasenna, correo, estatus, fechacreacion, fechamodificacion, pregunta, respuesta, idgrupo, idpersona) FROM stdin;
1	202cb962ac59075b964b07152d234b70	andy@prime.com	Activo	2017-03-22 08:50:08.293	\N	prime	prime	1	1
2	202cb962ac59075b964b07152d234b70	jose@prime.com	Activo	2017-03-22 08:50:08.433	\N	prime	prime	2	2
3	202cb962ac59075b964b07152d234b70	javier@prime.com	Activo	2017-03-22 08:50:08.477	\N	prime	prime	3	3
4	Por definir6129151654	jaivandres@gmail.com	Activo	2017-03-22 14:27:54.129496	\N	Por definir	Por definir	6	4
5	Por definir2325452940	jaivan@maia.com.ve	Activo	2017-03-22 14:30:37.014187	\N	Por definir	Por definir	6	5
\.


--
-- TOC entry 3634 (class 0 OID 0)
-- Dependencies: 355
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_id_seq', 3, true);


--
-- TOC entry 3395 (class 0 OID 96716)
-- Dependencies: 267
-- Data for Name: usuariohabilidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuariohabilidad (id, habilidad_id, usuario_id) FROM stdin;
\.


--
-- TOC entry 3635 (class 0 OID 0)
-- Dependencies: 354
-- Name: usuariohabilidad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuariohabilidad_id_seq', 1, false);


--
-- TOC entry 3396 (class 0 OID 96721)
-- Dependencies: 268
-- Data for Name: vehiculo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY vehiculo (id, anno, estatus, fechacreacion, fechamodificacion, kilometraje, nropuestos, placa, serialcarroceria, serialmotor, idcaja, idclase, idcolor, idcombustible, idgrosoraceite, idmarca, idmodelo, idrefrigerante, idtipoaceite, iduso, idusuario) FROM stdin;
2	0	Activo	2017-03-22 14:27:54.667863	\N	0	0	Por definir4923564425	Por definir	Por definir	\N	\N	\N	\N	\N	15	\N	\N	\N	\N	4
3	0	Activo	2017-03-22 14:27:54.889735	\N	0	0	Por definir0595004814	Por definir	Por definir	\N	\N	\N	\N	\N	16	\N	\N	\N	\N	4
4	0	Activo	2017-03-22 14:27:55.078165	\N	0	0	Por definir6061830285	Por definir	Por definir	\N	\N	\N	\N	\N	17	\N	\N	\N	\N	4
5	0	Activo	2017-03-22 14:27:55.211143	\N	0	0	Por definir9959330449	Por definir	Por definir	\N	\N	\N	\N	\N	18	\N	\N	\N	\N	4
6	0	Activo	2017-03-22 14:27:55.289006	\N	0	0	Por definir5027534727	Por definir	Por definir	\N	\N	\N	\N	\N	19	\N	\N	\N	\N	4
7	0	Activo	2017-03-22 14:27:55.377569	\N	0	0	Por definir6929745111	Por definir	Por definir	\N	\N	\N	\N	\N	20	\N	\N	\N	\N	4
1	2000	Activo	2017-03-22 18:57:54.38145	\N	0	0	LP-09	Por definir	Por definir	\N	\N	\N	\N	\N	16	139	\N	\N	\N	4
9	0	Activo	2017-03-22 14:30:37.071333	\N	0	0	Por definir7670571511	Por definir	Por definir	\N	\N	\N	\N	\N	4	\N	\N	\N	\N	5
10	0	Activo	2017-03-22 14:30:37.146873	\N	0	0	Por definir7590713862	Por definir	Por definir	\N	\N	\N	\N	\N	6	\N	\N	\N	\N	5
11	0	Activo	2017-03-22 14:30:37.160019	\N	0	0	Por definir2264266789	Por definir	Por definir	\N	\N	\N	\N	\N	7	\N	\N	\N	\N	5
12	0	Activo	2017-03-22 14:30:37.170886	\N	0	0	Por definir4897192727	Por definir	Por definir	\N	\N	\N	\N	\N	9	\N	\N	\N	\N	5
8	8000	Activo	2017-03-22 19:00:37.042521	\N	0	0	LP-60	Por definir	Por definir	\N	\N	\N	\N	\N	6	40	\N	\N	\N	5
\.


--
-- TOC entry 3636 (class 0 OID 0)
-- Dependencies: 357
-- Name: vehiculo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('vehiculo_id_seq', 1, false);


--
-- TOC entry 3397 (class 0 OID 96731)
-- Dependencies: 269
-- Data for Name: vehiculoaccesorio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY vehiculoaccesorio (id, estatus, fechacreacion, fechamodificacion, idaccesorio, idvehiculo) FROM stdin;
\.


--
-- TOC entry 3637 (class 0 OID 0)
-- Dependencies: 356
-- Name: vehiculoaccesorio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('vehiculoaccesorio_id_seq', 1, false);


--
-- TOC entry 3398 (class 0 OID 96736)
-- Dependencies: 270
-- Data for Name: vehiculoservicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY vehiculoservicio (id, estatus, fechacreacion, fechamodificacion, idservicio, idvehiculo) FROM stdin;
\.


--
-- TOC entry 3638 (class 0 OID 0)
-- Dependencies: 358
-- Name: vehiculoservicio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('vehiculoservicio_id_seq', 1, false);


--
-- TOC entry 3399 (class 0 OID 96741)
-- Dependencies: 271
-- Data for Name: viaje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY viaje (id, descripcion, estatus, fechacreacion, fechamodificacion) FROM stdin;
1	Medanos de Coro	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
2	Playa Chichiriviche	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
3	Cubiro	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
4	Playa los Roques	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
5	Parque Nacional Yacambu	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
6	Merida	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
7	Playa Choron	Activo	2017-01-01 16:00:00	2017-01-01 16:00:00
\.


--
-- TOC entry 3639 (class 0 OID 0)
-- Dependencies: 359
-- Name: viaje_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('viaje_id_seq', 7, true);


--
-- TOC entry 2778 (class 2606 OID 96015)
-- Name: accesorio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accesorio
    ADD CONSTRAINT accesorio_pkey PRIMARY KEY (id);


--
-- TOC entry 2780 (class 2606 OID 96025)
-- Name: accion_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accion
    ADD CONSTRAINT accion_nombre_key UNIQUE (nombre);


--
-- TOC entry 2782 (class 2606 OID 96023)
-- Name: accion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accion
    ADD CONSTRAINT accion_pkey PRIMARY KEY (id);


--
-- TOC entry 2784 (class 2606 OID 96033)
-- Name: aceite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aceite
    ADD CONSTRAINT aceite_pkey PRIMARY KEY (id);


--
-- TOC entry 2786 (class 2606 OID 96038)
-- Name: agenda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY agenda
    ADD CONSTRAINT agenda_pkey PRIMARY KEY (id);


--
-- TOC entry 2788 (class 2606 OID 96048)
-- Name: anomalia_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY anomalia
    ADD CONSTRAINT anomalia_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2790 (class 2606 OID 96046)
-- Name: anomalia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY anomalia
    ADD CONSTRAINT anomalia_pkey PRIMARY KEY (id);


--
-- TOC entry 2792 (class 2606 OID 96058)
-- Name: asunto_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY asunto
    ADD CONSTRAINT asunto_nombre_key UNIQUE (nombre);


--
-- TOC entry 2794 (class 2606 OID 96056)
-- Name: asunto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY asunto
    ADD CONSTRAINT asunto_pkey PRIMARY KEY (id);


--
-- TOC entry 3050 (class 2606 OID 97408)
-- Name: auth_group_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_group
    ADD CONSTRAINT auth_group_name_key UNIQUE (name);


--
-- TOC entry 3056 (class 2606 OID 97463)
-- Name: auth_group_permissions_group_id_0cd325b0_uniq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_group_permissions
    ADD CONSTRAINT auth_group_permissions_group_id_0cd325b0_uniq UNIQUE (group_id, permission_id);


--
-- TOC entry 3058 (class 2606 OID 97416)
-- Name: auth_group_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_group_permissions
    ADD CONSTRAINT auth_group_permissions_pkey PRIMARY KEY (id);


--
-- TOC entry 3052 (class 2606 OID 97406)
-- Name: auth_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_group
    ADD CONSTRAINT auth_group_pkey PRIMARY KEY (id);


--
-- TOC entry 3045 (class 2606 OID 97449)
-- Name: auth_permission_content_type_id_01ab375a_uniq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_permission
    ADD CONSTRAINT auth_permission_content_type_id_01ab375a_uniq UNIQUE (content_type_id, codename);


--
-- TOC entry 3047 (class 2606 OID 97398)
-- Name: auth_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_permission
    ADD CONSTRAINT auth_permission_pkey PRIMARY KEY (id);


--
-- TOC entry 3067 (class 2606 OID 97434)
-- Name: auth_user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user_groups
    ADD CONSTRAINT auth_user_groups_pkey PRIMARY KEY (id);


--
-- TOC entry 3069 (class 2606 OID 97478)
-- Name: auth_user_groups_user_id_94350c0c_uniq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user_groups
    ADD CONSTRAINT auth_user_groups_user_id_94350c0c_uniq UNIQUE (user_id, group_id);


--
-- TOC entry 3060 (class 2606 OID 97424)
-- Name: auth_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user
    ADD CONSTRAINT auth_user_pkey PRIMARY KEY (id);


--
-- TOC entry 3073 (class 2606 OID 97442)
-- Name: auth_user_user_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user_user_permissions
    ADD CONSTRAINT auth_user_user_permissions_pkey PRIMARY KEY (id);


--
-- TOC entry 3075 (class 2606 OID 97492)
-- Name: auth_user_user_permissions_user_id_14a6b632_uniq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user_user_permissions
    ADD CONSTRAINT auth_user_user_permissions_user_id_14a6b632_uniq UNIQUE (user_id, permission_id);


--
-- TOC entry 3063 (class 2606 OID 97521)
-- Name: auth_user_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user
    ADD CONSTRAINT auth_user_username_key UNIQUE (username);


--
-- TOC entry 2796 (class 2606 OID 96066)
-- Name: caja_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY caja
    ADD CONSTRAINT caja_pkey PRIMARY KEY (id);


--
-- TOC entry 2798 (class 2606 OID 96074)
-- Name: calificacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY calificacion
    ADD CONSTRAINT calificacion_pkey PRIMARY KEY (id);


--
-- TOC entry 2800 (class 2606 OID 96082)
-- Name: cita_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT cita_pkey PRIMARY KEY (id);


--
-- TOC entry 2802 (class 2606 OID 96090)
-- Name: ciudad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ciudad
    ADD CONSTRAINT ciudad_pkey PRIMARY KEY (id);


--
-- TOC entry 2804 (class 2606 OID 96098)
-- Name: clase_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clase
    ADD CONSTRAINT clase_pkey PRIMARY KEY (id);


--
-- TOC entry 2806 (class 2606 OID 96108)
-- Name: color_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY color
    ADD CONSTRAINT color_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2808 (class 2606 OID 96106)
-- Name: color_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY color
    ADD CONSTRAINT color_pkey PRIMARY KEY (id);


--
-- TOC entry 2810 (class 2606 OID 96116)
-- Name: combustible_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY combustible
    ADD CONSTRAINT combustible_pkey PRIMARY KEY (id);


--
-- TOC entry 2812 (class 2606 OID 96121)
-- Name: configuracionservicio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuracionservicio
    ADD CONSTRAINT configuracionservicio_pkey PRIMARY KEY (id);


--
-- TOC entry 3084 (class 2606 OID 97551)
-- Name: constance_config_key_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY constance_config
    ADD CONSTRAINT constance_config_key_key UNIQUE (key);


--
-- TOC entry 3086 (class 2606 OID 97549)
-- Name: constance_config_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY constance_config
    ADD CONSTRAINT constance_config_pkey PRIMARY KEY (id);


--
-- TOC entry 3081 (class 2606 OID 97538)
-- Name: dashboard_userdashboardmodule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dashboard_userdashboardmodule
    ADD CONSTRAINT dashboard_userdashboardmodule_pkey PRIMARY KEY (id);


--
-- TOC entry 2814 (class 2606 OID 96131)
-- Name: deporte_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY deporte
    ADD CONSTRAINT deporte_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2816 (class 2606 OID 96129)
-- Name: deporte_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY deporte
    ADD CONSTRAINT deporte_pkey PRIMARY KEY (id);


--
-- TOC entry 3079 (class 2606 OID 97506)
-- Name: django_admin_log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY django_admin_log
    ADD CONSTRAINT django_admin_log_pkey PRIMARY KEY (id);


--
-- TOC entry 3040 (class 2606 OID 97390)
-- Name: django_content_type_app_label_76bd3d3b_uniq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY django_content_type
    ADD CONSTRAINT django_content_type_app_label_76bd3d3b_uniq UNIQUE (app_label, model);


--
-- TOC entry 3042 (class 2606 OID 97388)
-- Name: django_content_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY django_content_type
    ADD CONSTRAINT django_content_type_pkey PRIMARY KEY (id);


--
-- TOC entry 3038 (class 2606 OID 97380)
-- Name: django_migrations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY django_migrations
    ADD CONSTRAINT django_migrations_pkey PRIMARY KEY (id);


--
-- TOC entry 3093 (class 2606 OID 97604)
-- Name: django_session_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY django_session
    ADD CONSTRAINT django_session_pkey PRIMARY KEY (session_key);


--
-- TOC entry 2818 (class 2606 OID 96139)
-- Name: espacio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espacio
    ADD CONSTRAINT espacio_pkey PRIMARY KEY (id);


--
-- TOC entry 2820 (class 2606 OID 96144)
-- Name: espacioherramienta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espacioherramienta
    ADD CONSTRAINT espacioherramienta_pkey PRIMARY KEY (id);


--
-- TOC entry 2822 (class 2606 OID 96149)
-- Name: espaciomecanico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espaciomecanico
    ADD CONSTRAINT espaciomecanico_pkey PRIMARY KEY (id);


--
-- TOC entry 2824 (class 2606 OID 96154)
-- Name: espacioordenservicio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espacioordenservicio
    ADD CONSTRAINT espacioordenservicio_pkey PRIMARY KEY (id);


--
-- TOC entry 2826 (class 2606 OID 96159)
-- Name: espaciotecnologia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espaciotecnologia
    ADD CONSTRAINT espaciotecnologia_pkey PRIMARY KEY (id);


--
-- TOC entry 2828 (class 2606 OID 96167)
-- Name: estado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);


--
-- TOC entry 2830 (class 2606 OID 96175)
-- Name: etapa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY etapa
    ADD CONSTRAINT etapa_pkey PRIMARY KEY (id);


--
-- TOC entry 2832 (class 2606 OID 96183)
-- Name: eventualidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY eventualidad
    ADD CONSTRAINT eventualidad_pkey PRIMARY KEY (id);


--
-- TOC entry 2834 (class 2606 OID 96191)
-- Name: falla_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY falla
    ADD CONSTRAINT falla_pkey PRIMARY KEY (id);


--
-- TOC entry 2836 (class 2606 OID 96196)
-- Name: fallapresupuesto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fallapresupuesto
    ADD CONSTRAINT fallapresupuesto_pkey PRIMARY KEY (id);


--
-- TOC entry 2838 (class 2606 OID 96206)
-- Name: funcion_clave_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcion
    ADD CONSTRAINT funcion_clave_key UNIQUE (clave);


--
-- TOC entry 2840 (class 2606 OID 96204)
-- Name: funcion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcion
    ADD CONSTRAINT funcion_pkey PRIMARY KEY (id);


--
-- TOC entry 2842 (class 2606 OID 96211)
-- Name: garaje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY garaje
    ADD CONSTRAINT garaje_pkey PRIMARY KEY (garaje_id);


--
-- TOC entry 2844 (class 2606 OID 96221)
-- Name: garantia_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY garantia
    ADD CONSTRAINT garantia_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2846 (class 2606 OID 96219)
-- Name: garantia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY garantia
    ADD CONSTRAINT garantia_pkey PRIMARY KEY (id);


--
-- TOC entry 2848 (class 2606 OID 96231)
-- Name: grosoraceite_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grosoraceite
    ADD CONSTRAINT grosoraceite_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2850 (class 2606 OID 96229)
-- Name: grosoraceite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grosoraceite
    ADD CONSTRAINT grosoraceite_pkey PRIMARY KEY (id);


--
-- TOC entry 2852 (class 2606 OID 96241)
-- Name: grupo_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupo
    ADD CONSTRAINT grupo_nombre_key UNIQUE (nombre);


--
-- TOC entry 2854 (class 2606 OID 96239)
-- Name: grupo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupo
    ADD CONSTRAINT grupo_pkey PRIMARY KEY (id);


--
-- TOC entry 2856 (class 2606 OID 96251)
-- Name: habilidad_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY habilidad
    ADD CONSTRAINT habilidad_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2858 (class 2606 OID 96249)
-- Name: habilidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY habilidad
    ADD CONSTRAINT habilidad_pkey PRIMARY KEY (id);


--
-- TOC entry 2860 (class 2606 OID 96259)
-- Name: herramienta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY herramienta
    ADD CONSTRAINT herramienta_pkey PRIMARY KEY (id);


--
-- TOC entry 2862 (class 2606 OID 96269)
-- Name: horario_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY horario
    ADD CONSTRAINT horario_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2864 (class 2606 OID 96267)
-- Name: horario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY horario
    ADD CONSTRAINT horario_pkey PRIMARY KEY (id);


--
-- TOC entry 3088 (class 2606 OID 97561)
-- Name: jet_bookmark_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jet_bookmark
    ADD CONSTRAINT jet_bookmark_pkey PRIMARY KEY (id);


--
-- TOC entry 3090 (class 2606 OID 97570)
-- Name: jet_pinnedapplication_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jet_pinnedapplication
    ADD CONSTRAINT jet_pinnedapplication_pkey PRIMARY KEY (id);


--
-- TOC entry 2866 (class 2606 OID 96279)
-- Name: job_clave_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_clave_key UNIQUE (clave);


--
-- TOC entry 2868 (class 2606 OID 96277)
-- Name: job_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_pkey PRIMARY KEY (id);


--
-- TOC entry 2870 (class 2606 OID 96284)
-- Name: log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY log
    ADD CONSTRAINT log_pkey PRIMARY KEY (id);


--
-- TOC entry 2872 (class 2606 OID 96292)
-- Name: marca_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (id);


--
-- TOC entry 2874 (class 2606 OID 96302)
-- Name: marcarepuesto_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marcarepuesto
    ADD CONSTRAINT marcarepuesto_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2876 (class 2606 OID 96300)
-- Name: marcarepuesto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marcarepuesto
    ADD CONSTRAINT marcarepuesto_pkey PRIMARY KEY (id);


--
-- TOC entry 2878 (class 2606 OID 96310)
-- Name: marcatecnologia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marcatecnologia
    ADD CONSTRAINT marcatecnologia_pkey PRIMARY KEY (id);


--
-- TOC entry 2880 (class 2606 OID 96320)
-- Name: modelo_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY modelo
    ADD CONSTRAINT modelo_nombre_key UNIQUE (nombre);


--
-- TOC entry 2882 (class 2606 OID 96318)
-- Name: modelo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY modelo
    ADD CONSTRAINT modelo_pkey PRIMARY KEY (id);


--
-- TOC entry 2884 (class 2606 OID 96328)
-- Name: motivo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY motivo
    ADD CONSTRAINT motivo_pkey PRIMARY KEY (id);


--
-- TOC entry 2886 (class 2606 OID 96336)
-- Name: motor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY motor
    ADD CONSTRAINT motor_pkey PRIMARY KEY (id);


--
-- TOC entry 2888 (class 2606 OID 97585)
-- Name: noticia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY noticia
    ADD CONSTRAINT noticia_pkey PRIMARY KEY (id);


--
-- TOC entry 2890 (class 2606 OID 96346)
-- Name: noticia_titulo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY noticia
    ADD CONSTRAINT noticia_titulo_key UNIQUE (titulo);


--
-- TOC entry 2892 (class 2606 OID 96354)
-- Name: notificacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion
    ADD CONSTRAINT notificacion_pkey PRIMARY KEY (id);


--
-- TOC entry 2894 (class 2606 OID 96359)
-- Name: notificacionusuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacionusuario
    ADD CONSTRAINT notificacionusuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2896 (class 2606 OID 96369)
-- Name: ocupacion_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ocupacion
    ADD CONSTRAINT ocupacion_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2898 (class 2606 OID 96367)
-- Name: ocupacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ocupacion
    ADD CONSTRAINT ocupacion_pkey PRIMARY KEY (id);


--
-- TOC entry 2900 (class 2606 OID 96379)
-- Name: ordenentrega_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordenentrega
    ADD CONSTRAINT ordenentrega_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2902 (class 2606 OID 96377)
-- Name: ordenentrega_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordenentrega
    ADD CONSTRAINT ordenentrega_pkey PRIMARY KEY (id);


--
-- TOC entry 2904 (class 2606 OID 96387)
-- Name: ordenservicio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordenservicio
    ADD CONSTRAINT ordenservicio_pkey PRIMARY KEY (id);


--
-- TOC entry 2906 (class 2606 OID 96392)
-- Name: ordenserviciousuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordenserviciousuario
    ADD CONSTRAINT ordenserviciousuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2908 (class 2606 OID 96402)
-- Name: pasatiempo_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pasatiempo
    ADD CONSTRAINT pasatiempo_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2910 (class 2606 OID 96400)
-- Name: pasatiempo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pasatiempo
    ADD CONSTRAINT pasatiempo_pkey PRIMARY KEY (id);


--
-- TOC entry 2912 (class 2606 OID 96407)
-- Name: permiso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permiso
    ADD CONSTRAINT permiso_pkey PRIMARY KEY (id);


--
-- TOC entry 2914 (class 2606 OID 96417)
-- Name: persona_cedula_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT persona_cedula_key UNIQUE (cedula);


--
-- TOC entry 2916 (class 2606 OID 96415)
-- Name: persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id);


--
-- TOC entry 2918 (class 2606 OID 96425)
-- Name: preguntafrecuente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY preguntafrecuente
    ADD CONSTRAINT preguntafrecuente_pkey PRIMARY KEY (id);


--
-- TOC entry 2920 (class 2606 OID 96433)
-- Name: presupuesto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (id);


--
-- TOC entry 2922 (class 2606 OID 96438)
-- Name: presupuestoservicio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuestoservicio
    ADD CONSTRAINT presupuestoservicio_pkey PRIMARY KEY (id);


--
-- TOC entry 2924 (class 2606 OID 96443)
-- Name: presupuestotiporepuesto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuestotiporepuesto
    ADD CONSTRAINT presupuestotiporepuesto_pkey PRIMARY KEY (id);


--
-- TOC entry 2926 (class 2606 OID 96453)
-- Name: profesion_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY profesion
    ADD CONSTRAINT profesion_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2928 (class 2606 OID 96451)
-- Name: profesion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY profesion
    ADD CONSTRAINT profesion_pkey PRIMARY KEY (id);


--
-- TOC entry 2930 (class 2606 OID 96461)
-- Name: promocion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promocion
    ADD CONSTRAINT promocion_pkey PRIMARY KEY (id);


--
-- TOC entry 2932 (class 2606 OID 96463)
-- Name: promocion_titulo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promocion
    ADD CONSTRAINT promocion_titulo_key UNIQUE (titulo);


--
-- TOC entry 2934 (class 2606 OID 96471)
-- Name: prueba_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prueba
    ADD CONSTRAINT prueba_pkey PRIMARY KEY (id);


--
-- TOC entry 2936 (class 2606 OID 96481)
-- Name: reclamo_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reclamo
    ADD CONSTRAINT reclamo_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2938 (class 2606 OID 96479)
-- Name: reclamo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reclamo
    ADD CONSTRAINT reclamo_pkey PRIMARY KEY (id);


--
-- TOC entry 2940 (class 2606 OID 96491)
-- Name: rectificacion_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rectificacion
    ADD CONSTRAINT rectificacion_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2942 (class 2606 OID 96489)
-- Name: rectificacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rectificacion
    ADD CONSTRAINT rectificacion_pkey PRIMARY KEY (id);


--
-- TOC entry 2944 (class 2606 OID 96501)
-- Name: redsocial_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY redsocial
    ADD CONSTRAINT redsocial_nombre_key UNIQUE (nombre);


--
-- TOC entry 2946 (class 2606 OID 96499)
-- Name: redsocial_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY redsocial
    ADD CONSTRAINT redsocial_pkey PRIMARY KEY (id);


--
-- TOC entry 2948 (class 2606 OID 96509)
-- Name: refrigerante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY refrigerante
    ADD CONSTRAINT refrigerante_pkey PRIMARY KEY (id);


--
-- TOC entry 2950 (class 2606 OID 96517)
-- Name: servicio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (id);


--
-- TOC entry 2952 (class 2606 OID 96522)
-- Name: serviciosetapas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY serviciosetapas
    ADD CONSTRAINT serviciosetapas_pkey PRIMARY KEY (id);


--
-- TOC entry 2954 (class 2606 OID 96527)
-- Name: serviciosherramientas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY serviciosherramientas
    ADD CONSTRAINT serviciosherramientas_pkey PRIMARY KEY (id);


--
-- TOC entry 2956 (class 2606 OID 96532)
-- Name: serviciostecnologias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY serviciostecnologias
    ADD CONSTRAINT serviciostecnologias_pkey PRIMARY KEY (id);


--
-- TOC entry 2958 (class 2606 OID 96537)
-- Name: sesion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sesion
    ADD CONSTRAINT sesion_pkey PRIMARY KEY (seseion_id);


--
-- TOC entry 2960 (class 2606 OID 96547)
-- Name: sistema_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sistema
    ADD CONSTRAINT sistema_nombre_key UNIQUE (nombre);


--
-- TOC entry 2962 (class 2606 OID 96545)
-- Name: sistema_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sistema
    ADD CONSTRAINT sistema_pkey PRIMARY KEY (id);


--
-- TOC entry 2964 (class 2606 OID 96557)
-- Name: slider_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY slider
    ADD CONSTRAINT slider_nombre_key UNIQUE (nombre);


--
-- TOC entry 2966 (class 2606 OID 96555)
-- Name: slider_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY slider
    ADD CONSTRAINT slider_pkey PRIMARY KEY (id);


--
-- TOC entry 2968 (class 2606 OID 96565)
-- Name: tarifa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tarifa
    ADD CONSTRAINT tarifa_pkey PRIMARY KEY (id);


--
-- TOC entry 2970 (class 2606 OID 96573)
-- Name: tecnologia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tecnologia
    ADD CONSTRAINT tecnologia_pkey PRIMARY KEY (id);


--
-- TOC entry 3097 (class 2606 OID 97614)
-- Name: thumbnail_kvstore_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY thumbnail_kvstore
    ADD CONSTRAINT thumbnail_kvstore_pkey PRIMARY KEY (key);


--
-- TOC entry 2972 (class 2606 OID 96583)
-- Name: tipoaceite_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipoaceite
    ADD CONSTRAINT tipoaceite_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2974 (class 2606 OID 96581)
-- Name: tipoaceite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipoaceite
    ADD CONSTRAINT tipoaceite_pkey PRIMARY KEY (id);


--
-- TOC entry 2976 (class 2606 OID 96593)
-- Name: tipocaja_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipocaja
    ADD CONSTRAINT tipocaja_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2978 (class 2606 OID 96591)
-- Name: tipocaja_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipocaja
    ADD CONSTRAINT tipocaja_pkey PRIMARY KEY (id);


--
-- TOC entry 2980 (class 2606 OID 96603)
-- Name: tipoclase_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipoclase
    ADD CONSTRAINT tipoclase_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2982 (class 2606 OID 96601)
-- Name: tipoclase_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipoclase
    ADD CONSTRAINT tipoclase_pkey PRIMARY KEY (id);


--
-- TOC entry 2984 (class 2606 OID 96613)
-- Name: tipocombustible_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipocombustible
    ADD CONSTRAINT tipocombustible_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2986 (class 2606 OID 96611)
-- Name: tipocombustible_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipocombustible
    ADD CONSTRAINT tipocombustible_pkey PRIMARY KEY (id);


--
-- TOC entry 2988 (class 2606 OID 96623)
-- Name: tipoeventualidad_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipoeventualidad
    ADD CONSTRAINT tipoeventualidad_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2990 (class 2606 OID 96621)
-- Name: tipoeventualidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipoeventualidad
    ADD CONSTRAINT tipoeventualidad_pkey PRIMARY KEY (id);


--
-- TOC entry 2992 (class 2606 OID 96633)
-- Name: tipogarantia_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipogarantia
    ADD CONSTRAINT tipogarantia_nombre_key UNIQUE (nombre);


--
-- TOC entry 2994 (class 2606 OID 96631)
-- Name: tipogarantia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipogarantia
    ADD CONSTRAINT tipogarantia_pkey PRIMARY KEY (id);


--
-- TOC entry 2996 (class 2606 OID 96643)
-- Name: tipomotor_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipomotor
    ADD CONSTRAINT tipomotor_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 2998 (class 2606 OID 96641)
-- Name: tipomotor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipomotor
    ADD CONSTRAINT tipomotor_pkey PRIMARY KEY (id);


--
-- TOC entry 3000 (class 2606 OID 96651)
-- Name: tiponotificacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiponotificacion
    ADD CONSTRAINT tiponotificacion_pkey PRIMARY KEY (id);


--
-- TOC entry 3002 (class 2606 OID 96661)
-- Name: tiporeclamo_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 3004 (class 2606 OID 96659)
-- Name: tiporeclamo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiporeclamo
    ADD CONSTRAINT tiporeclamo_pkey PRIMARY KEY (id);


--
-- TOC entry 3006 (class 2606 OID 96671)
-- Name: tiporefrigerante_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiporefrigerante
    ADD CONSTRAINT tiporefrigerante_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 3008 (class 2606 OID 96669)
-- Name: tiporefrigerante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiporefrigerante
    ADD CONSTRAINT tiporefrigerante_pkey PRIMARY KEY (id);


--
-- TOC entry 3010 (class 2606 OID 96679)
-- Name: tiporepuesto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiporepuesto
    ADD CONSTRAINT tiporepuesto_pkey PRIMARY KEY (id);


--
-- TOC entry 3012 (class 2606 OID 96687)
-- Name: tiposervicio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiposervicio
    ADD CONSTRAINT tiposervicio_pkey PRIMARY KEY (id);


--
-- TOC entry 3014 (class 2606 OID 96695)
-- Name: tipotecnologia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipotecnologia
    ADD CONSTRAINT tipotecnologia_pkey PRIMARY KEY (id);


--
-- TOC entry 3016 (class 2606 OID 96705)
-- Name: uso_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY uso
    ADD CONSTRAINT uso_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 3018 (class 2606 OID 96703)
-- Name: uso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY uso
    ADD CONSTRAINT uso_pkey PRIMARY KEY (id);


--
-- TOC entry 3020 (class 2606 OID 96715)
-- Name: usuario_correo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_correo_key UNIQUE (correo);


--
-- TOC entry 3022 (class 2606 OID 96713)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 3024 (class 2606 OID 96720)
-- Name: usuariohabilidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuariohabilidad
    ADD CONSTRAINT usuariohabilidad_pkey PRIMARY KEY (id);


--
-- TOC entry 3026 (class 2606 OID 96728)
-- Name: vehiculo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT vehiculo_pkey PRIMARY KEY (id);


--
-- TOC entry 3028 (class 2606 OID 96730)
-- Name: vehiculo_placa_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT vehiculo_placa_key UNIQUE (placa);


--
-- TOC entry 3030 (class 2606 OID 96735)
-- Name: vehiculoaccesorio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculoaccesorio
    ADD CONSTRAINT vehiculoaccesorio_pkey PRIMARY KEY (id);


--
-- TOC entry 3032 (class 2606 OID 96740)
-- Name: vehiculoservicio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculoservicio
    ADD CONSTRAINT vehiculoservicio_pkey PRIMARY KEY (id);


--
-- TOC entry 3034 (class 2606 OID 96750)
-- Name: viaje_descripcion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY viaje
    ADD CONSTRAINT viaje_descripcion_key UNIQUE (descripcion);


--
-- TOC entry 3036 (class 2606 OID 96748)
-- Name: viaje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY viaje
    ADD CONSTRAINT viaje_pkey PRIMARY KEY (id);


--
-- TOC entry 3048 (class 1259 OID 97451)
-- Name: auth_group_name_a6ea08ec_like; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX auth_group_name_a6ea08ec_like ON auth_group USING btree (name varchar_pattern_ops);


--
-- TOC entry 3053 (class 1259 OID 97464)
-- Name: auth_group_permissions_0e939a4f; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX auth_group_permissions_0e939a4f ON auth_group_permissions USING btree (group_id);


--
-- TOC entry 3054 (class 1259 OID 97465)
-- Name: auth_group_permissions_8373b171; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX auth_group_permissions_8373b171 ON auth_group_permissions USING btree (permission_id);


--
-- TOC entry 3043 (class 1259 OID 97450)
-- Name: auth_permission_417f1b1c; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX auth_permission_417f1b1c ON auth_permission USING btree (content_type_id);


--
-- TOC entry 3064 (class 1259 OID 97480)
-- Name: auth_user_groups_0e939a4f; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX auth_user_groups_0e939a4f ON auth_user_groups USING btree (group_id);


--
-- TOC entry 3065 (class 1259 OID 97479)
-- Name: auth_user_groups_e8701ad4; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX auth_user_groups_e8701ad4 ON auth_user_groups USING btree (user_id);


--
-- TOC entry 3070 (class 1259 OID 97494)
-- Name: auth_user_user_permissions_8373b171; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX auth_user_user_permissions_8373b171 ON auth_user_user_permissions USING btree (permission_id);


--
-- TOC entry 3071 (class 1259 OID 97493)
-- Name: auth_user_user_permissions_e8701ad4; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX auth_user_user_permissions_e8701ad4 ON auth_user_user_permissions USING btree (user_id);


--
-- TOC entry 3061 (class 1259 OID 97522)
-- Name: auth_user_username_6821ab7c_like; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX auth_user_username_6821ab7c_like ON auth_user USING btree (username varchar_pattern_ops);


--
-- TOC entry 3082 (class 1259 OID 97552)
-- Name: constance_config_key_baef3136_like; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX constance_config_key_baef3136_like ON constance_config USING btree (key varchar_pattern_ops);


--
-- TOC entry 3076 (class 1259 OID 97517)
-- Name: django_admin_log_417f1b1c; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX django_admin_log_417f1b1c ON django_admin_log USING btree (content_type_id);


--
-- TOC entry 3077 (class 1259 OID 97518)
-- Name: django_admin_log_e8701ad4; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX django_admin_log_e8701ad4 ON django_admin_log USING btree (user_id);


--
-- TOC entry 3091 (class 1259 OID 97605)
-- Name: django_session_de54fa62; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX django_session_de54fa62 ON django_session USING btree (expire_date);


--
-- TOC entry 3094 (class 1259 OID 97606)
-- Name: django_session_session_key_c0390e0f_like; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX django_session_session_key_c0390e0f_like ON django_session USING btree (session_key varchar_pattern_ops);


--
-- TOC entry 3095 (class 1259 OID 97615)
-- Name: thumbnail_kvstore_key_3f850178_like; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX thumbnail_kvstore_key_3f850178_like ON thumbnail_kvstore USING btree (key varchar_pattern_ops);


--
-- TOC entry 3188 (class 2606 OID 97457)
-- Name: auth_group_permiss_permission_id_84c5c92e_fk_auth_permission_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_group_permissions
    ADD CONSTRAINT auth_group_permiss_permission_id_84c5c92e_fk_auth_permission_id FOREIGN KEY (permission_id) REFERENCES auth_permission(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3187 (class 2606 OID 97452)
-- Name: auth_group_permissions_group_id_b120cbf9_fk_auth_group_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_group_permissions
    ADD CONSTRAINT auth_group_permissions_group_id_b120cbf9_fk_auth_group_id FOREIGN KEY (group_id) REFERENCES auth_group(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3186 (class 2606 OID 97443)
-- Name: auth_permiss_content_type_id_2f476e4b_fk_django_content_type_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_permission
    ADD CONSTRAINT auth_permiss_content_type_id_2f476e4b_fk_django_content_type_id FOREIGN KEY (content_type_id) REFERENCES django_content_type(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3190 (class 2606 OID 97472)
-- Name: auth_user_groups_group_id_97559544_fk_auth_group_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user_groups
    ADD CONSTRAINT auth_user_groups_group_id_97559544_fk_auth_group_id FOREIGN KEY (group_id) REFERENCES auth_group(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3189 (class 2606 OID 97467)
-- Name: auth_user_groups_user_id_6a12ed8b_fk_auth_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user_groups
    ADD CONSTRAINT auth_user_groups_user_id_6a12ed8b_fk_auth_user_id FOREIGN KEY (user_id) REFERENCES auth_user(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3192 (class 2606 OID 97486)
-- Name: auth_user_user_per_permission_id_1fbb5f2c_fk_auth_permission_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user_user_permissions
    ADD CONSTRAINT auth_user_user_per_permission_id_1fbb5f2c_fk_auth_permission_id FOREIGN KEY (permission_id) REFERENCES auth_permission(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3191 (class 2606 OID 97481)
-- Name: auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user_user_permissions
    ADD CONSTRAINT auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id FOREIGN KEY (user_id) REFERENCES auth_user(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3193 (class 2606 OID 97507)
-- Name: django_admin_content_type_id_c4bce8eb_fk_django_content_type_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY django_admin_log
    ADD CONSTRAINT django_admin_content_type_id_c4bce8eb_fk_django_content_type_id FOREIGN KEY (content_type_id) REFERENCES django_content_type(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3194 (class 2606 OID 97512)
-- Name: django_admin_log_user_id_c564eba6_fk_auth_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY django_admin_log
    ADD CONSTRAINT django_admin_log_user_id_c564eba6_fk_auth_user_id FOREIGN KEY (user_id) REFERENCES auth_user(id) DEFERRABLE INITIALLY DEFERRED;


--
-- TOC entry 3101 (class 2606 OID 96766)
-- Name: fk11ceb8219ae58fb2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY calificacion
    ADD CONSTRAINT fk11ceb8219ae58fb2 FOREIGN KEY (idordenentrega) REFERENCES ordenentrega(id);


--
-- TOC entry 3150 (class 2606 OID 97011)
-- Name: fk16c7a1896ede3622; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuestoservicio
    ADD CONSTRAINT fk16c7a1896ede3622 FOREIGN KEY (idservicio) REFERENCES servicio(id);


--
-- TOC entry 3149 (class 2606 OID 97006)
-- Name: fk16c7a189cc1c9f30; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuestoservicio
    ADD CONSTRAINT fk16c7a189cc1c9f30 FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(id);


--
-- TOC entry 3100 (class 2606 OID 96761)
-- Name: fk1fee35de25af28; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY caja
    ADD CONSTRAINT fk1fee35de25af28 FOREIGN KEY (idtipocaja) REFERENCES tipocaja(id);


--
-- TOC entry 3104 (class 2606 OID 96781)
-- Name: fk200d7324b8fef2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT fk200d7324b8fef2 FOREIGN KEY (idpromocion) REFERENCES promocion(id);


--
-- TOC entry 3107 (class 2606 OID 96796)
-- Name: fk200d736ede3622; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT fk200d736ede3622 FOREIGN KEY (idservicio) REFERENCES servicio(id);


--
-- TOC entry 3106 (class 2606 OID 96791)
-- Name: fk200d73abf1a75e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT fk200d73abf1a75e FOREIGN KEY (idespacio) REFERENCES espacio(id);


--
-- TOC entry 3103 (class 2606 OID 96776)
-- Name: fk200d73ba910740; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT fk200d73ba910740 FOREIGN KEY (idvehiculo) REFERENCES vehiculo(id);


--
-- TOC entry 3105 (class 2606 OID 96786)
-- Name: fk200d73e862257e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT fk200d73e862257e FOREIGN KEY (ideventualidad) REFERENCES eventualidad(id);


--
-- TOC entry 3102 (class 2606 OID 96771)
-- Name: fk200d73eee03b16; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT fk200d73eee03b16 FOREIGN KEY (idmotivo) REFERENCES motivo(id);


--
-- TOC entry 3159 (class 2606 OID 97056)
-- Name: fk27a8ef88a61013ce; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY refrigerante
    ADD CONSTRAINT fk27a8ef88a61013ce FOREIGN KEY (idtiporefrigerante) REFERENCES tiporefrigerante(id);


--
-- TOC entry 3131 (class 2606 OID 96916)
-- Name: fk2d3e22bc24b8fef2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion
    ADD CONSTRAINT fk2d3e22bc24b8fef2 FOREIGN KEY (idpromocion) REFERENCES promocion(id);


--
-- TOC entry 3129 (class 2606 OID 96906)
-- Name: fk2d3e22bc9ae58fb2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion
    ADD CONSTRAINT fk2d3e22bc9ae58fb2 FOREIGN KEY (idordenentrega) REFERENCES ordenentrega(id);


--
-- TOC entry 3130 (class 2606 OID 96911)
-- Name: fk2d3e22bcb13a7a36; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion
    ADD CONSTRAINT fk2d3e22bcb13a7a36 FOREIGN KEY (idtiponotificacion) REFERENCES tiponotificacion(id);


--
-- TOC entry 3132 (class 2606 OID 96921)
-- Name: fk2d3e22bccc1c9f30; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion
    ADD CONSTRAINT fk2d3e22bccc1c9f30 FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(id);


--
-- TOC entry 3133 (class 2606 OID 96926)
-- Name: fk2d3e22bce862257e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion
    ADD CONSTRAINT fk2d3e22bce862257e FOREIGN KEY (ideventualidad) REFERENCES eventualidad(id);


--
-- TOC entry 3143 (class 2606 OID 96976)
-- Name: fk3ac597751c5b2252; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permiso
    ADD CONSTRAINT fk3ac597751c5b2252 FOREIGN KEY (idfuncion) REFERENCES funcion(id);


--
-- TOC entry 3142 (class 2606 OID 96971)
-- Name: fk3ac5977582ed481c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permiso
    ADD CONSTRAINT fk3ac5977582ed481c FOREIGN KEY (idgrupo) REFERENCES grupo(id);


--
-- TOC entry 3141 (class 2606 OID 96966)
-- Name: fk3ac59775c48a41c4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permiso
    ADD CONSTRAINT fk3ac59775c48a41c4 FOREIGN KEY (idaccion) REFERENCES accion(id);


--
-- TOC entry 3144 (class 2606 OID 96981)
-- Name: fk3ac8678c126aad10; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT fk3ac8678c126aad10 FOREIGN KEY (iddeporte) REFERENCES deporte(id);


--
-- TOC entry 3146 (class 2606 OID 96991)
-- Name: fk3ac8678c1c79d9ac; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT fk3ac8678c1c79d9ac FOREIGN KEY (idpasatiempo) REFERENCES pasatiempo(id);


--
-- TOC entry 3145 (class 2606 OID 96986)
-- Name: fk3ac8678c534a2778; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT fk3ac8678c534a2778 FOREIGN KEY (idocupacion) REFERENCES ocupacion(id);


--
-- TOC entry 3147 (class 2606 OID 96996)
-- Name: fk3ac8678cbc9dc0c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT fk3ac8678cbc9dc0c FOREIGN KEY (idprofesion) REFERENCES profesion(id);


--
-- TOC entry 3163 (class 2606 OID 97076)
-- Name: fk3c50a89a6ede3622; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY serviciosherramientas
    ADD CONSTRAINT fk3c50a89a6ede3622 FOREIGN KEY (idservicio) REFERENCES servicio(id);


--
-- TOC entry 3162 (class 2606 OID 97071)
-- Name: fk3c50a89a9e0432b6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY serviciosherramientas
    ADD CONSTRAINT fk3c50a89a9e0432b6 FOREIGN KEY (idherramienta) REFERENCES herramienta(id);


--
-- TOC entry 3118 (class 2606 OID 96851)
-- Name: fk3cb3d2b3abf1a75e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espaciotecnologia
    ADD CONSTRAINT fk3cb3d2b3abf1a75e FOREIGN KEY (idespacio) REFERENCES espacio(id);


--
-- TOC entry 3119 (class 2606 OID 96856)
-- Name: fk3cb3d2b3b37242c8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espaciotecnologia
    ADD CONSTRAINT fk3cb3d2b3b37242c8 FOREIGN KEY (idtecnologia) REFERENCES tecnologia(id);


--
-- TOC entry 3109 (class 2606 OID 96806)
-- Name: fk3e2b94ae699f796; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clase
    ADD CONSTRAINT fk3e2b94ae699f796 FOREIGN KEY (idtipoclase) REFERENCES tipoclase(id);


--
-- TOC entry 3136 (class 2606 OID 96941)
-- Name: fk4075f32e8455744; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordenentrega
    ADD CONSTRAINT fk4075f32e8455744 FOREIGN KEY (idgarantia) REFERENCES garantia(id);


--
-- TOC entry 3137 (class 2606 OID 96946)
-- Name: fk4075f32ef972a198; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordenentrega
    ADD CONSTRAINT fk4075f32ef972a198 FOREIGN KEY (idprueba) REFERENCES prueba(id);


--
-- TOC entry 3124 (class 2606 OID 96881)
-- Name: fk44d7e6a45365190e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcion
    ADD CONSTRAINT fk44d7e6a45365190e FOREIGN KEY (idsistema) REFERENCES sistema(id);


--
-- TOC entry 3123 (class 2606 OID 96876)
-- Name: fk44d7e6a45e6a3366; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcion
    ADD CONSTRAINT fk44d7e6a45e6a3366 FOREIGN KEY (idfuncionpadre) REFERENCES funcion(id);


--
-- TOC entry 3128 (class 2606 OID 96901)
-- Name: fk4714855e7b715ac; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY motor
    ADD CONSTRAINT fk4714855e7b715ac FOREIGN KEY (idtipomotor) REFERENCES tipomotor(id);


--
-- TOC entry 3153 (class 2606 OID 97026)
-- Name: fk47c762346ede3622; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promocion
    ADD CONSTRAINT fk47c762346ede3622 FOREIGN KEY (idservicio) REFERENCES servicio(id);


--
-- TOC entry 3111 (class 2606 OID 96816)
-- Name: fk4c66e87d6ede3622; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuracionservicio
    ADD CONSTRAINT fk4c66e87d6ede3622 FOREIGN KEY (idservicio) REFERENCES servicio(id);


--
-- TOC entry 3169 (class 2606 OID 97106)
-- Name: fk50c6ee7c7b22f13f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuariohabilidad
    ADD CONSTRAINT fk50c6ee7c7b22f13f FOREIGN KEY (usuario_id) REFERENCES usuario(id);


--
-- TOC entry 3170 (class 2606 OID 97111)
-- Name: fk50c6ee7cabbc2a7f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuariohabilidad
    ADD CONSTRAINT fk50c6ee7cabbc2a7f FOREIGN KEY (habilidad_id) REFERENCES habilidad(id);


--
-- TOC entry 3117 (class 2606 OID 96846)
-- Name: fk53bcd36abf1a75e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espacioordenservicio
    ADD CONSTRAINT fk53bcd36abf1a75e FOREIGN KEY (idespacio) REFERENCES espacio(id);


--
-- TOC entry 3140 (class 2606 OID 96961)
-- Name: fk5a93a4ee49466b26; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordenserviciousuario
    ADD CONSTRAINT fk5a93a4ee49466b26 FOREIGN KEY (idusuario) REFERENCES usuario(id);


--
-- TOC entry 3139 (class 2606 OID 96956)
-- Name: fk5a93a4ee5fde05aa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordenserviciousuario
    ADD CONSTRAINT fk5a93a4ee5fde05aa FOREIGN KEY (idordenservicio) REFERENCES ordenservicio(id);


--
-- TOC entry 3168 (class 2606 OID 97101)
-- Name: fk5b4d8b0e82ed481c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk5b4d8b0e82ed481c FOREIGN KEY (idgrupo) REFERENCES grupo(id);


--
-- TOC entry 3167 (class 2606 OID 97096)
-- Name: fk5b4d8b0e83c2422; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk5b4d8b0e83c2422 FOREIGN KEY (idpersona) REFERENCES persona(id);


--
-- TOC entry 3148 (class 2606 OID 97001)
-- Name: fk6c4b6f9356c9f21c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT fk6c4b6f9356c9f21c FOREIGN KEY (idcita) REFERENCES cita(id);


--
-- TOC entry 3098 (class 2606 OID 96751)
-- Name: fk748d9957bc9ef968; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aceite
    ADD CONSTRAINT fk748d9957bc9ef968 FOREIGN KEY (idgrosoraceite) REFERENCES grosoraceite(id);


--
-- TOC entry 3099 (class 2606 OID 96756)
-- Name: fk748d9957e4d74dec; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aceite
    ADD CONSTRAINT fk748d9957e4d74dec FOREIGN KEY (idtipoaceite) REFERENCES tipoaceite(id);


--
-- TOC entry 3108 (class 2606 OID 96801)
-- Name: fk78530878d4032cc2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ciudad
    ADD CONSTRAINT fk78530878d4032cc2 FOREIGN KEY (idestado) REFERENCES estado(id);


--
-- TOC entry 3181 (class 2606 OID 97166)
-- Name: fk7be8dd8549466b26; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd8549466b26 FOREIGN KEY (idusuario) REFERENCES usuario(id);


--
-- TOC entry 3179 (class 2606 OID 97156)
-- Name: fk7be8dd8556c9b3a0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd8556c9b3a0 FOREIGN KEY (idcaja) REFERENCES caja(id);


--
-- TOC entry 3173 (class 2606 OID 97126)
-- Name: fk7be8dd8565e604ec; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd8565e604ec FOREIGN KEY (iduso) REFERENCES uso(id);


--
-- TOC entry 3171 (class 2606 OID 97116)
-- Name: fk7be8dd8575acd158; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd8575acd158 FOREIGN KEY (idcombustible) REFERENCES combustible(id);


--
-- TOC entry 3177 (class 2606 OID 97146)
-- Name: fk7be8dd858276821e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd858276821e FOREIGN KEY (idclase) REFERENCES clase(id);


--
-- TOC entry 3175 (class 2606 OID 97136)
-- Name: fk7be8dd8582798e10; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd8582798e10 FOREIGN KEY (idcolor) REFERENCES color(id);


--
-- TOC entry 3174 (class 2606 OID 97131)
-- Name: fk7be8dd858386d3c2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd858386d3c2 FOREIGN KEY (idmarca) REFERENCES marca(id);


--
-- TOC entry 3178 (class 2606 OID 97151)
-- Name: fk7be8dd85bc9ef968; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd85bc9ef968 FOREIGN KEY (idgrosoraceite) REFERENCES grosoraceite(id);


--
-- TOC entry 3172 (class 2606 OID 97121)
-- Name: fk7be8dd85cc842046; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd85cc842046 FOREIGN KEY (idrefrigerante) REFERENCES refrigerante(id);


--
-- TOC entry 3180 (class 2606 OID 97161)
-- Name: fk7be8dd85e4d74dec; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd85e4d74dec FOREIGN KEY (idtipoaceite) REFERENCES tipoaceite(id);


--
-- TOC entry 3176 (class 2606 OID 97141)
-- Name: fk7be8dd85eed18ec2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculo
    ADD CONSTRAINT fk7be8dd85eed18ec2 FOREIGN KEY (idmodelo) REFERENCES modelo(id);


--
-- TOC entry 3125 (class 2606 OID 96886)
-- Name: fk7eb447a47b22f13f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY garaje
    ADD CONSTRAINT fk7eb447a47b22f13f FOREIGN KEY (usuario_id) REFERENCES usuario(id);


--
-- TOC entry 3185 (class 2606 OID 97186)
-- Name: fk8558fd7b6ede3622; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculoservicio
    ADD CONSTRAINT fk8558fd7b6ede3622 FOREIGN KEY (idservicio) REFERENCES servicio(id);


--
-- TOC entry 3184 (class 2606 OID 97181)
-- Name: fk8558fd7bba910740; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculoservicio
    ADD CONSTRAINT fk8558fd7bba910740 FOREIGN KEY (idvehiculo) REFERENCES vehiculo(id);


--
-- TOC entry 3127 (class 2606 OID 96896)
-- Name: fk89b056868386d3c2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY modelo
    ADD CONSTRAINT fk89b056868386d3c2 FOREIGN KEY (idmarca) REFERENCES marca(id);


--
-- TOC entry 3154 (class 2606 OID 97031)
-- Name: fk8f00dff15fde05aa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prueba
    ADD CONSTRAINT fk8f00dff15fde05aa FOREIGN KEY (idordenservicio) REFERENCES ordenservicio(id);


--
-- TOC entry 3166 (class 2606 OID 97091)
-- Name: fk936760277b22f13f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sesion
    ADD CONSTRAINT fk936760277b22f13f FOREIGN KEY (usuario_id) REFERENCES usuario(id);


--
-- TOC entry 3138 (class 2606 OID 96951)
-- Name: fk9d514220cc1c9f30; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordenservicio
    ADD CONSTRAINT fk9d514220cc1c9f30 FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(id);


--
-- TOC entry 3135 (class 2606 OID 96936)
-- Name: fk9da21cd249466b26; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacionusuario
    ADD CONSTRAINT fk9da21cd249466b26 FOREIGN KEY (idusuario) REFERENCES usuario(id);


--
-- TOC entry 3134 (class 2606 OID 96931)
-- Name: fk9da21cd2d7ae86ae; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacionusuario
    ADD CONSTRAINT fk9da21cd2d7ae86ae FOREIGN KEY (idnotificacion) REFERENCES notificacion(id);


--
-- TOC entry 3164 (class 2606 OID 97081)
-- Name: fka0df292d6ede3622; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY serviciostecnologias
    ADD CONSTRAINT fka0df292d6ede3622 FOREIGN KEY (idservicio) REFERENCES servicio(id);


--
-- TOC entry 3165 (class 2606 OID 97086)
-- Name: fka0df292db37242c8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY serviciostecnologias
    ADD CONSTRAINT fka0df292db37242c8 FOREIGN KEY (idtecnologia) REFERENCES tecnologia(id);


--
-- TOC entry 3126 (class 2606 OID 96891)
-- Name: fka2c3058742a84ecc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY garantia
    ADD CONSTRAINT fka2c3058742a84ecc FOREIGN KEY (idtipogarantia) REFERENCES tipogarantia(id);


--
-- TOC entry 3155 (class 2606 OID 97036)
-- Name: fka3be1a479ae58fb2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reclamo
    ADD CONSTRAINT fka3be1a479ae58fb2 FOREIGN KEY (idordenentrega) REFERENCES ordenentrega(id);


--
-- TOC entry 3156 (class 2606 OID 97041)
-- Name: fka3be1a47c3438110; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reclamo
    ADD CONSTRAINT fka3be1a47c3438110 FOREIGN KEY (idtiporeclamo) REFERENCES tiporeclamo(id);


--
-- TOC entry 3157 (class 2606 OID 97046)
-- Name: fka3be1a47eee03b16; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reclamo
    ADD CONSTRAINT fka3be1a47eee03b16 FOREIGN KEY (idmotivo) REFERENCES motivo(id);


--
-- TOC entry 3161 (class 2606 OID 97066)
-- Name: fka5829a6d6ede3622; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY serviciosetapas
    ADD CONSTRAINT fka5829a6d6ede3622 FOREIGN KEY (idservicio) REFERENCES servicio(id);


--
-- TOC entry 3160 (class 2606 OID 97061)
-- Name: fka5829a6d82b62550; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY serviciosetapas
    ADD CONSTRAINT fka5829a6d82b62550 FOREIGN KEY (idetapa) REFERENCES etapa(id);


--
-- TOC entry 3182 (class 2606 OID 97171)
-- Name: fkb3472fb387e989ba; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculoaccesorio
    ADD CONSTRAINT fkb3472fb387e989ba FOREIGN KEY (idaccesorio) REFERENCES accesorio(id);


--
-- TOC entry 3183 (class 2606 OID 97176)
-- Name: fkb3472fb3ba910740; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehiculoaccesorio
    ADD CONSTRAINT fkb3472fb3ba910740 FOREIGN KEY (idvehiculo) REFERENCES vehiculo(id);


--
-- TOC entry 3120 (class 2606 OID 96861)
-- Name: fkb597f224c1ee1906; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY eventualidad
    ADD CONSTRAINT fkb597f224c1ee1906 FOREIGN KEY (idtipoeventualidad) REFERENCES tipoeventualidad(id);


--
-- TOC entry 3158 (class 2606 OID 97051)
-- Name: fkb79e6b3e5365190e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY redsocial
    ADD CONSTRAINT fkb79e6b3e5365190e FOREIGN KEY (idsistema) REFERENCES sistema(id);


--
-- TOC entry 3110 (class 2606 OID 96811)
-- Name: fkc11388a7f8904cd0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY combustible
    ADD CONSTRAINT fkc11388a7f8904cd0 FOREIGN KEY (idtipocombustible) REFERENCES tipocombustible(id);


--
-- TOC entry 3152 (class 2606 OID 97021)
-- Name: fkc573c9a8cc1c9f30; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuestotiporepuesto
    ADD CONSTRAINT fkc573c9a8cc1c9f30 FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(id);


--
-- TOC entry 3151 (class 2606 OID 97016)
-- Name: fkc573c9a8d28c1a40; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuestotiporepuesto
    ADD CONSTRAINT fkc573c9a8d28c1a40 FOREIGN KEY (idtiporepuesto) REFERENCES tiporepuesto(id);


--
-- TOC entry 3112 (class 2606 OID 96821)
-- Name: fkca3292ac4fcf20e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espacio
    ADD CONSTRAINT fkca3292ac4fcf20e FOREIGN KEY (idagenda) REFERENCES agenda(id);


--
-- TOC entry 3113 (class 2606 OID 96826)
-- Name: fkcb5d91ec9e0432b6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espacioherramienta
    ADD CONSTRAINT fkcb5d91ec9e0432b6 FOREIGN KEY (idherramienta) REFERENCES herramienta(id);


--
-- TOC entry 3114 (class 2606 OID 96831)
-- Name: fkcb5d91ecabf1a75e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espacioherramienta
    ADD CONSTRAINT fkcb5d91ecabf1a75e FOREIGN KEY (idespacio) REFERENCES espacio(id);


--
-- TOC entry 3116 (class 2606 OID 96841)
-- Name: fkceae83a7abf1a75e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espaciomecanico
    ADD CONSTRAINT fkceae83a7abf1a75e FOREIGN KEY (idespacio) REFERENCES espacio(id);


--
-- TOC entry 3115 (class 2606 OID 96836)
-- Name: fkceae83a7d234ebeb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY espaciomecanico
    ADD CONSTRAINT fkceae83a7d234ebeb FOREIGN KEY (idmecanico) REFERENCES usuario(id);


--
-- TOC entry 3121 (class 2606 OID 96866)
-- Name: fkf978d3ad82c15fd6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fallapresupuesto
    ADD CONSTRAINT fkf978d3ad82c15fd6 FOREIGN KEY (idfalla) REFERENCES falla(id);


--
-- TOC entry 3122 (class 2606 OID 96871)
-- Name: fkf978d3adcc1c9f30; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fallapresupuesto
    ADD CONSTRAINT fkf978d3adcc1c9f30 FOREIGN KEY (idpresupuesto) REFERENCES presupuesto(id);


--
-- TOC entry 3523 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-03-22 10:14:46 VET

--
-- PostgreSQL database dump complete
--

