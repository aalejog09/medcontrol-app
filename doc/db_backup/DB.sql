PGDMP                  	    |            medcontrol_db    16.3    16.3    [           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            \           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ]           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ^           1262    25052    medcontrol_db    DATABASE     �   CREATE DATABASE medcontrol_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE medcontrol_db;
                postgres    false            _           0    0    DATABASE medcontrol_db    COMMENT     h   COMMENT ON DATABASE medcontrol_db IS 'Base de datos para el sistema de registro y control SMO2H HMVSS';
                   postgres    false    5214                       1255    25213    audit_function()    FUNCTION     �  CREATE FUNCTION public.audit_function() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO auditing_entity (table_name, record_id, action, user_id, observation, new_values)
        VALUES (TG_TABLE_NAME, NEW.id, 'INSERT', NEW.user_id, 'Registro creado', row_to_json(NEW));
        RETURN NEW;
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO auditing_entity (table_name, record_id, action, user_id, observation, old_values, new_values)
        VALUES (TG_TABLE_NAME, NEW.id, 'UPDATE', NEW.user_id, 'Registro modificado', row_to_json(OLD), row_to_json(NEW));
        RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO auditing_entity (table_name, record_id, action, user_id, observation, old_values)
        VALUES (TG_TABLE_NAME, OLD.id, 'DELETE', OLD.user_id, 'Registro eliminado', row_to_json(OLD));
        RETURN OLD;
    END IF;
END;
$$;
 '   DROP FUNCTION public.audit_function();
       public          postgres    false            
           1259    36574 	   body_part    TABLE     �   CREATE TABLE public.body_part (
    id bigint NOT NULL,
    description character varying(255),
    name character varying(255),
    physical_exam_id bigint
);
    DROP TABLE public.body_part;
       public         heap    postgres    false            	           1259    36573    body_part_id_seq    SEQUENCE     y   CREATE SEQUENCE public.body_part_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.body_part_id_seq;
       public          postgres    false    266            `           0    0    body_part_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.body_part_id_seq OWNED BY public.body_part.id;
          public          postgres    false    265            �            1259    35284    city    TABLE     �   CREATE TABLE public.city (
    id bigint NOT NULL,
    capital boolean NOT NULL,
    city_name character varying(255),
    state_id bigint
);
    DROP TABLE public.city;
       public         heap    postgres    false            �            1259    35283    city_id_seq    SEQUENCE     t   CREATE SEQUENCE public.city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.city_id_seq;
       public          postgres    false    216            a           0    0    city_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;
          public          postgres    false    215            �            1259    35291    contact_data    TABLE     �   CREATE TABLE public.contact_data (
    id bigint NOT NULL,
    additional_email character varying(45),
    additional_phone_number character varying(30),
    email_principal character varying(45),
    phone_number_principal character varying(30)
);
     DROP TABLE public.contact_data;
       public         heap    postgres    false            �            1259    35290    contact_data_id_seq    SEQUENCE     |   CREATE SEQUENCE public.contact_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.contact_data_id_seq;
       public          postgres    false    218            b           0    0    contact_data_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.contact_data_id_seq OWNED BY public.contact_data.id;
          public          postgres    false    217            �            1259    35298    examination_studies    TABLE       CREATE TABLE public.examination_studies (
    id bigint NOT NULL,
    code character varying(255),
    name character varying(255) NOT NULL,
    path character varying(255) NOT NULL,
    registry_date timestamp(6) without time zone NOT NULL,
    patient_id bigint NOT NULL
);
 '   DROP TABLE public.examination_studies;
       public         heap    postgres    false            �            1259    35297    examination_studies_id_seq    SEQUENCE     �   CREATE SEQUENCE public.examination_studies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.examination_studies_id_seq;
       public          postgres    false    220            c           0    0    examination_studies_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.examination_studies_id_seq OWNED BY public.examination_studies.id;
          public          postgres    false    219            �            1259    35307    familiar_surgical_history    TABLE     �   CREATE TABLE public.familiar_surgical_history (
    id bigint NOT NULL,
    disease_name character varying(200),
    family_relationship character varying(200),
    patients_id bigint,
    observation character varying(200)
);
 -   DROP TABLE public.familiar_surgical_history;
       public         heap    postgres    false            �            1259    35306     familiar_surgical_history_id_seq    SEQUENCE     �   CREATE SEQUENCE public.familiar_surgical_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.familiar_surgical_history_id_seq;
       public          postgres    false    222            d           0    0     familiar_surgical_history_id_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.familiar_surgical_history_id_seq OWNED BY public.familiar_surgical_history.id;
          public          postgres    false    221            �            1259    35314    function    TABLE        CREATE TABLE public.function (
    id integer NOT NULL,
    creation_date timestamp(6) without time zone,
    description character varying(255),
    enabled smallint NOT NULL,
    CONSTRAINT function_enabled_check CHECK ((enabled = ANY (ARRAY[0, 1])))
);
    DROP TABLE public.function;
       public         heap    postgres    false            �            1259    35313    function_id_seq    SEQUENCE     �   CREATE SEQUENCE public.function_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.function_id_seq;
       public          postgres    false    224            e           0    0    function_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.function_id_seq OWNED BY public.function.id;
          public          postgres    false    223            �            1259    35320    lab_order_laboratories    TABLE     t   CREATE TABLE public.lab_order_laboratories (
    lab_order_id bigint NOT NULL,
    laboratory_id bigint NOT NULL
);
 *   DROP TABLE public.lab_order_laboratories;
       public         heap    postgres    false            �            1259    35324 
   lab_orders    TABLE     �   CREATE TABLE public.lab_orders (
    id bigint NOT NULL,
    date date NOT NULL,
    user_id bigint NOT NULL,
    patient_id bigint NOT NULL
);
    DROP TABLE public.lab_orders;
       public         heap    postgres    false            �            1259    35323    lab_orders_id_seq    SEQUENCE     z   CREATE SEQUENCE public.lab_orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.lab_orders_id_seq;
       public          postgres    false    227            f           0    0    lab_orders_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.lab_orders_id_seq OWNED BY public.lab_orders.id;
          public          postgres    false    226            �            1259    35331    laboratories    TABLE     g   CREATE TABLE public.laboratories (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);
     DROP TABLE public.laboratories;
       public         heap    postgres    false            �            1259    35330    laboratories_id_seq    SEQUENCE     |   CREATE SEQUENCE public.laboratories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.laboratories_id_seq;
       public          postgres    false    229            g           0    0    laboratories_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.laboratories_id_seq OWNED BY public.laboratories.id;
          public          postgres    false    228            �            1259    35338    location_data    TABLE     �   CREATE TABLE public.location_data (
    id bigint NOT NULL,
    additional_location_info character varying(200) NOT NULL,
    housing character varying(200) NOT NULL,
    city_id bigint NOT NULL
);
 !   DROP TABLE public.location_data;
       public         heap    postgres    false            �            1259    35337    location_data_id_seq    SEQUENCE     }   CREATE SEQUENCE public.location_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.location_data_id_seq;
       public          postgres    false    231            h           0    0    location_data_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.location_data_id_seq OWNED BY public.location_data.id;
          public          postgres    false    230                       1259    36089    medical_consultation    TABLE       CREATE TABLE public.medical_consultation (
    id bigint NOT NULL,
    code character varying(200) NOT NULL,
    diagnosis character varying(200) NOT NULL,
    first_consult smallint NOT NULL,
    reason character varying(200) NOT NULL,
    registry_date timestamp(6) without time zone NOT NULL,
    treatment character varying(200) NOT NULL,
    patient_id bigint,
    specialist_id bigint,
    vital_signals_id bigint,
    CONSTRAINT medical_consultation_first_consult_check CHECK ((first_consult = ANY (ARRAY[0, 1])))
);
 (   DROP TABLE public.medical_consultation;
       public         heap    postgres    false                       1259    36088    medical_consultation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.medical_consultation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.medical_consultation_id_seq;
       public          postgres    false    260            i           0    0    medical_consultation_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.medical_consultation_id_seq OWNED BY public.medical_consultation.id;
          public          postgres    false    259                       1259    36099    medical_records    TABLE     �  CREATE TABLE public.medical_records (
    id bigint NOT NULL,
    actual_disease character varying(30) NOT NULL,
    previous_diagnosis character varying(30) NOT NULL,
    previous_treatment character varying(30) NOT NULL,
    reason character varying(30) NOT NULL,
    registry_date timestamp(6) without time zone NOT NULL,
    user_id bigint NOT NULL,
    medical_service_id bigint,
    patient_id bigint,
    referred_medical_service_id bigint,
    vital_signals_id bigint
);
 #   DROP TABLE public.medical_records;
       public         heap    postgres    false                       1259    36098    medical_records_id_seq    SEQUENCE        CREATE SEQUENCE public.medical_records_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.medical_records_id_seq;
       public          postgres    false    262            j           0    0    medical_records_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.medical_records_id_seq OWNED BY public.medical_records.id;
          public          postgres    false    261            �            1259    35362    medical_service    TABLE     �   CREATE TABLE public.medical_service (
    id bigint NOT NULL,
    name character varying(200) NOT NULL,
    responsible_user_id bigint
);
 #   DROP TABLE public.medical_service;
       public         heap    postgres    false            �            1259    35361    medical_service_id_seq    SEQUENCE        CREATE SEQUENCE public.medical_service_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.medical_service_id_seq;
       public          postgres    false    233            k           0    0    medical_service_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.medical_service_id_seq OWNED BY public.medical_service.id;
          public          postgres    false    232                       1259    35667    municipality    TABLE     �   CREATE TABLE public.municipality (
    id bigint NOT NULL,
    municipality_name character varying(100) NOT NULL,
    state_id bigint NOT NULL
);
     DROP TABLE public.municipality;
       public         heap    postgres    false                       1259    35666    municipality_id_seq    SEQUENCE     |   CREATE SEQUENCE public.municipality_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.municipality_id_seq;
       public          postgres    false    258            l           0    0    municipality_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.municipality_id_seq OWNED BY public.municipality.id;
          public          postgres    false    257            �            1259    35376    parish    TABLE     �   CREATE TABLE public.parish (
    id bigint NOT NULL,
    parish_name character varying(100) NOT NULL,
    municipality_id bigint NOT NULL
);
    DROP TABLE public.parish;
       public         heap    postgres    false            �            1259    35375    parish_id_seq    SEQUENCE     v   CREATE SEQUENCE public.parish_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.parish_id_seq;
       public          postgres    false    235            m           0    0    parish_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.parish_id_seq OWNED BY public.parish.id;
          public          postgres    false    234                       1259    36628    patients_data    TABLE       CREATE TABLE public.patients_data (
    id bigint NOT NULL,
    born_location character varying(100),
    patient_observation character varying(30),
    patient_code character varying(30),
    patient_type character varying(30),
    personal_data_id bigint
);
 !   DROP TABLE public.patients_data;
       public         heap    postgres    false                       1259    36627    patients_data_id_seq    SEQUENCE     }   CREATE SEQUENCE public.patients_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.patients_data_id_seq;
       public          postgres    false    274            n           0    0    patients_data_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.patients_data_id_seq OWNED BY public.patients_data.id;
          public          postgres    false    273            �            1259    35389    patients_data_representative    TABLE     �   CREATE TABLE public.patients_data_representative (
    patients_data_id bigint NOT NULL,
    patients_representative_id bigint NOT NULL
);
 0   DROP TABLE public.patients_data_representative;
       public         heap    postgres    false            �            1259    35395    patients_representative    TABLE     �   CREATE TABLE public.patients_representative (
    id bigint NOT NULL,
    family_relationship character varying(30),
    personal_data_id bigint
);
 +   DROP TABLE public.patients_representative;
       public         heap    postgres    false            �            1259    35394    patients_representative_id_seq    SEQUENCE     �   CREATE SEQUENCE public.patients_representative_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.patients_representative_id_seq;
       public          postgres    false    238            o           0    0    patients_representative_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.patients_representative_id_seq OWNED BY public.patients_representative.id;
          public          postgres    false    237            �            1259    35402    personal_data    TABLE     !  CREATE TABLE public.personal_data (
    id bigint NOT NULL,
    born_date date,
    civil_state character varying(30),
    education_level character varying(30),
    dni character varying(30),
    lastnames character varying(60),
    names character varying(30) NOT NULL,
    profession character varying(100),
    registry_date timestamp(6) without time zone NOT NULL,
    sex character varying(30),
    contact_id bigint NOT NULL,
    location_id bigint NOT NULL,
    occupation character varying(30),
    nationality character varying(30)
);
 !   DROP TABLE public.personal_data;
       public         heap    postgres    false            �            1259    35401    personal_data_id_seq    SEQUENCE     }   CREATE SEQUENCE public.personal_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.personal_data_id_seq;
       public          postgres    false    240            p           0    0    personal_data_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.personal_data_id_seq OWNED BY public.personal_data.id;
          public          postgres    false    239            �            1259    35409    personal_surgical_history    TABLE       CREATE TABLE public.personal_surgical_history (
    id bigint NOT NULL,
    diagnosis character varying(200),
    disease_name character varying(200),
    intervention_date timestamp(6) without time zone,
    treatment character varying(200),
    patients_id bigint
);
 -   DROP TABLE public.personal_surgical_history;
       public         heap    postgres    false            �            1259    35408     personal_surgical_history_id_seq    SEQUENCE     �   CREATE SEQUENCE public.personal_surgical_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.personal_surgical_history_id_seq;
       public          postgres    false    242            q           0    0     personal_surgical_history_id_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.personal_surgical_history_id_seq OWNED BY public.personal_surgical_history.id;
          public          postgres    false    241                       1259    36585    physical_exam    TABLE     :  CREATE TABLE public.physical_exam (
    id bigint NOT NULL,
    diastolic_blood_pressure integer,
    hear_rate integer,
    height double precision,
    oxygen_saturation integer,
    sistolic_blood_pressure integer,
    weigh double precision,
    patient_id bigint,
    physical_general_conditions_id bigint
);
 !   DROP TABLE public.physical_exam;
       public         heap    postgres    false                       1259    36584    physical_exam_id_seq    SEQUENCE     }   CREATE SEQUENCE public.physical_exam_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.physical_exam_id_seq;
       public          postgres    false    268            r           0    0    physical_exam_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.physical_exam_id_seq OWNED BY public.physical_exam.id;
          public          postgres    false    267                       1259    36592    physical_general_condition    TABLE     0  CREATE TABLE public.physical_general_condition (
    id bigint NOT NULL,
    breathing character varying(30),
    capillary_filling character varying(30),
    hydration character varying(30),
    "position" character varying(30),
    state character varying(30),
    temperature character varying(30)
);
 .   DROP TABLE public.physical_general_condition;
       public         heap    postgres    false                       1259    36591 !   physical_general_condition_id_seq    SEQUENCE     �   CREATE SEQUENCE public.physical_general_condition_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.physical_general_condition_id_seq;
       public          postgres    false    270            s           0    0 !   physical_general_condition_id_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.physical_general_condition_id_seq OWNED BY public.physical_general_condition.id;
          public          postgres    false    269            �            1259    35418    piscobiological_habits    TABLE     �   CREATE TABLE public.piscobiological_habits (
    id bigint NOT NULL,
    frequency integer,
    frequency_rate character varying(200),
    habit character varying(200),
    quantity integer,
    quantity_rate integer,
    patients_id bigint
);
 *   DROP TABLE public.piscobiological_habits;
       public         heap    postgres    false            �            1259    35417    piscobiological_habits_id_seq    SEQUENCE     �   CREATE SEQUENCE public.piscobiological_habits_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.piscobiological_habits_id_seq;
       public          postgres    false    244            t           0    0    piscobiological_habits_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.piscobiological_habits_id_seq OWNED BY public.piscobiological_habits.id;
          public          postgres    false    243            �            1259    35425    role    TABLE     �   CREATE TABLE public.role (
    id integer NOT NULL,
    creation_date timestamp(6) without time zone,
    name character varying(255),
    enabled smallint,
    CONSTRAINT role_enabled_check CHECK ((enabled = ANY (ARRAY[0, 1])))
);
    DROP TABLE public.role;
       public         heap    postgres    false            �            1259    35432    role_function    TABLE     m   CREATE TABLE public.role_function (
    id integer NOT NULL,
    function_id integer,
    role_id integer
);
 !   DROP TABLE public.role_function;
       public         heap    postgres    false            �            1259    35431    role_function_id_seq    SEQUENCE     �   CREATE SEQUENCE public.role_function_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.role_function_id_seq;
       public          postgres    false    248            u           0    0    role_function_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.role_function_id_seq OWNED BY public.role_function.id;
          public          postgres    false    247            �            1259    35424    role_id_seq    SEQUENCE     �   CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.role_id_seq;
       public          postgres    false    246            v           0    0    role_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;
          public          postgres    false    245            �            1259    35439    specialist_data    TABLE        CREATE TABLE public.specialist_data (
    id bigint NOT NULL,
    medical_college_code character varying(200) NOT NULL,
    mpps_code character varying(200) NOT NULL,
    speciality character varying(200) NOT NULL,
    personal_data_id bigint,
    type character varying(200) NOT NULL
);
 #   DROP TABLE public.specialist_data;
       public         heap    postgres    false            �            1259    35438    specialist_data_id_seq    SEQUENCE        CREATE SEQUENCE public.specialist_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.specialist_data_id_seq;
       public          postgres    false    250            w           0    0    specialist_data_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.specialist_data_id_seq OWNED BY public.specialist_data.id;
          public          postgres    false    249            �            1259    35448    state    TABLE     �   CREATE TABLE public.state (
    id bigint NOT NULL,
    iso_3166_2_code character varying(4) NOT NULL,
    state_name character varying(250) NOT NULL
);
    DROP TABLE public.state;
       public         heap    postgres    false            �            1259    35447    state_id_seq    SEQUENCE     u   CREATE SEQUENCE public.state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.state_id_seq;
       public          postgres    false    252            x           0    0    state_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.state_id_seq OWNED BY public.state.id;
          public          postgres    false    251                       1259    36599    sub_part    TABLE     �   CREATE TABLE public.sub_part (
    id bigint NOT NULL,
    description character varying(100),
    name character varying(15),
    body_part_id bigint
);
    DROP TABLE public.sub_part;
       public         heap    postgres    false                       1259    36598    sub_part_id_seq    SEQUENCE     x   CREATE SEQUENCE public.sub_part_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.sub_part_id_seq;
       public          postgres    false    272            y           0    0    sub_part_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.sub_part_id_seq OWNED BY public.sub_part.id;
          public          postgres    false    271                       1259    36113    treatment_cycle    TABLE     �  CREATE TABLE public.treatment_cycle (
    id bigint NOT NULL,
    cycle_number integer NOT NULL,
    observations character varying(255),
    registry_date timestamp(6) without time zone,
    treatment character varying(255) NOT NULL,
    treatment_cycle_date timestamp(6) without time zone,
    treatment_rate character varying(255) NOT NULL,
    treatment_time character varying(255) NOT NULL,
    patient_id bigint,
    vital_signals_id bigint
);
 #   DROP TABLE public.treatment_cycle;
       public         heap    postgres    false                       1259    36112    treatment_cycle_id_seq    SEQUENCE        CREATE SEQUENCE public.treatment_cycle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.treatment_cycle_id_seq;
       public          postgres    false    264            z           0    0    treatment_cycle_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.treatment_cycle_id_seq OWNED BY public.treatment_cycle.id;
          public          postgres    false    263            �            1259    35464    users    TABLE     K  CREATE TABLE public.users (
    id bigint NOT NULL,
    credential_expired smallint,
    enabled smallint NOT NULL,
    expired smallint,
    locked smallint,
    password character varying(60),
    username character varying(30) NOT NULL,
    personal_data_id bigint,
    CONSTRAINT users_credential_expired_check CHECK ((credential_expired = ANY (ARRAY[0, 1]))),
    CONSTRAINT users_enabled_check CHECK ((enabled = ANY (ARRAY[0, 1]))),
    CONSTRAINT users_expired_check CHECK ((expired = ANY (ARRAY[0, 1]))),
    CONSTRAINT users_locked_check CHECK ((locked = ANY (ARRAY[0, 1])))
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    35463    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    254            {           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    253                        1259    35475 
   users_role    TABLE     e   CREATE TABLE public.users_role (
    id integer NOT NULL,
    role_id integer,
    user_id bigint
);
    DROP TABLE public.users_role;
       public         heap    postgres    false            �            1259    35474    users_role_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.users_role_id_seq;
       public          postgres    false    256            |           0    0    users_role_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.users_role_id_seq OWNED BY public.users_role.id;
          public          postgres    false    255                       1259    36692    vital_signals    TABLE     {  CREATE TABLE public.vital_signals (
    id bigint NOT NULL,
    diastolic_blood_pressure integer,
    oxygen_saturation double precision,
    respiratory_rate integer,
    rhythm_pulse character varying(255),
    systolic_blood_pressure integer,
    temperature double precision,
    medical_consultation_id bigint,
    medical_record_id bigint,
    treatment_cycle_id bigint
);
 !   DROP TABLE public.vital_signals;
       public         heap    postgres    false                       1259    36691    vital_signals_id_seq    SEQUENCE     }   CREATE SEQUENCE public.vital_signals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.vital_signals_id_seq;
       public          postgres    false    276            }           0    0    vital_signals_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.vital_signals_id_seq OWNED BY public.vital_signals.id;
          public          postgres    false    275                       2604    36577    body_part id    DEFAULT     l   ALTER TABLE ONLY public.body_part ALTER COLUMN id SET DEFAULT nextval('public.body_part_id_seq'::regclass);
 ;   ALTER TABLE public.body_part ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    265    266    266            �           2604    35287    city id    DEFAULT     b   ALTER TABLE ONLY public.city ALTER COLUMN id SET DEFAULT nextval('public.city_id_seq'::regclass);
 6   ALTER TABLE public.city ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            �           2604    35294    contact_data id    DEFAULT     r   ALTER TABLE ONLY public.contact_data ALTER COLUMN id SET DEFAULT nextval('public.contact_data_id_seq'::regclass);
 >   ALTER TABLE public.contact_data ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    35301    examination_studies id    DEFAULT     �   ALTER TABLE ONLY public.examination_studies ALTER COLUMN id SET DEFAULT nextval('public.examination_studies_id_seq'::regclass);
 E   ALTER TABLE public.examination_studies ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            �           2604    35310    familiar_surgical_history id    DEFAULT     �   ALTER TABLE ONLY public.familiar_surgical_history ALTER COLUMN id SET DEFAULT nextval('public.familiar_surgical_history_id_seq'::regclass);
 K   ALTER TABLE public.familiar_surgical_history ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    35317    function id    DEFAULT     j   ALTER TABLE ONLY public.function ALTER COLUMN id SET DEFAULT nextval('public.function_id_seq'::regclass);
 :   ALTER TABLE public.function ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    35327    lab_orders id    DEFAULT     n   ALTER TABLE ONLY public.lab_orders ALTER COLUMN id SET DEFAULT nextval('public.lab_orders_id_seq'::regclass);
 <   ALTER TABLE public.lab_orders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    227    227            �           2604    35334    laboratories id    DEFAULT     r   ALTER TABLE ONLY public.laboratories ALTER COLUMN id SET DEFAULT nextval('public.laboratories_id_seq'::regclass);
 >   ALTER TABLE public.laboratories ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    229    228    229            �           2604    35341    location_data id    DEFAULT     t   ALTER TABLE ONLY public.location_data ALTER COLUMN id SET DEFAULT nextval('public.location_data_id_seq'::regclass);
 ?   ALTER TABLE public.location_data ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    231    230    231            �           2604    36092    medical_consultation id    DEFAULT     �   ALTER TABLE ONLY public.medical_consultation ALTER COLUMN id SET DEFAULT nextval('public.medical_consultation_id_seq'::regclass);
 F   ALTER TABLE public.medical_consultation ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    259    260    260                        2604    36102    medical_records id    DEFAULT     x   ALTER TABLE ONLY public.medical_records ALTER COLUMN id SET DEFAULT nextval('public.medical_records_id_seq'::regclass);
 A   ALTER TABLE public.medical_records ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    261    262    262            �           2604    35365    medical_service id    DEFAULT     x   ALTER TABLE ONLY public.medical_service ALTER COLUMN id SET DEFAULT nextval('public.medical_service_id_seq'::regclass);
 A   ALTER TABLE public.medical_service ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    232    233            �           2604    35670    municipality id    DEFAULT     r   ALTER TABLE ONLY public.municipality ALTER COLUMN id SET DEFAULT nextval('public.municipality_id_seq'::regclass);
 >   ALTER TABLE public.municipality ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    257    258    258            �           2604    35379 	   parish id    DEFAULT     f   ALTER TABLE ONLY public.parish ALTER COLUMN id SET DEFAULT nextval('public.parish_id_seq'::regclass);
 8   ALTER TABLE public.parish ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    235    234    235                       2604    36631    patients_data id    DEFAULT     t   ALTER TABLE ONLY public.patients_data ALTER COLUMN id SET DEFAULT nextval('public.patients_data_id_seq'::regclass);
 ?   ALTER TABLE public.patients_data ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    274    273    274            �           2604    35398    patients_representative id    DEFAULT     �   ALTER TABLE ONLY public.patients_representative ALTER COLUMN id SET DEFAULT nextval('public.patients_representative_id_seq'::regclass);
 I   ALTER TABLE public.patients_representative ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    237    238    238            �           2604    35405    personal_data id    DEFAULT     t   ALTER TABLE ONLY public.personal_data ALTER COLUMN id SET DEFAULT nextval('public.personal_data_id_seq'::regclass);
 ?   ALTER TABLE public.personal_data ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    239    240    240            �           2604    35412    personal_surgical_history id    DEFAULT     �   ALTER TABLE ONLY public.personal_surgical_history ALTER COLUMN id SET DEFAULT nextval('public.personal_surgical_history_id_seq'::regclass);
 K   ALTER TABLE public.personal_surgical_history ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    241    242    242                       2604    36588    physical_exam id    DEFAULT     t   ALTER TABLE ONLY public.physical_exam ALTER COLUMN id SET DEFAULT nextval('public.physical_exam_id_seq'::regclass);
 ?   ALTER TABLE public.physical_exam ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    268    267    268                       2604    36595    physical_general_condition id    DEFAULT     �   ALTER TABLE ONLY public.physical_general_condition ALTER COLUMN id SET DEFAULT nextval('public.physical_general_condition_id_seq'::regclass);
 L   ALTER TABLE public.physical_general_condition ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    270    269    270            �           2604    35421    piscobiological_habits id    DEFAULT     �   ALTER TABLE ONLY public.piscobiological_habits ALTER COLUMN id SET DEFAULT nextval('public.piscobiological_habits_id_seq'::regclass);
 H   ALTER TABLE public.piscobiological_habits ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    243    244    244            �           2604    35428    role id    DEFAULT     b   ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);
 6   ALTER TABLE public.role ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    245    246    246            �           2604    35435    role_function id    DEFAULT     t   ALTER TABLE ONLY public.role_function ALTER COLUMN id SET DEFAULT nextval('public.role_function_id_seq'::regclass);
 ?   ALTER TABLE public.role_function ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    247    248    248            �           2604    35442    specialist_data id    DEFAULT     x   ALTER TABLE ONLY public.specialist_data ALTER COLUMN id SET DEFAULT nextval('public.specialist_data_id_seq'::regclass);
 A   ALTER TABLE public.specialist_data ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    249    250    250            �           2604    35451    state id    DEFAULT     d   ALTER TABLE ONLY public.state ALTER COLUMN id SET DEFAULT nextval('public.state_id_seq'::regclass);
 7   ALTER TABLE public.state ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    251    252    252                       2604    36602    sub_part id    DEFAULT     j   ALTER TABLE ONLY public.sub_part ALTER COLUMN id SET DEFAULT nextval('public.sub_part_id_seq'::regclass);
 :   ALTER TABLE public.sub_part ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    272    271    272                       2604    36116    treatment_cycle id    DEFAULT     x   ALTER TABLE ONLY public.treatment_cycle ALTER COLUMN id SET DEFAULT nextval('public.treatment_cycle_id_seq'::regclass);
 A   ALTER TABLE public.treatment_cycle ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    263    264    264            �           2604    35467    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    254    253    254            �           2604    35478    users_role id    DEFAULT     n   ALTER TABLE ONLY public.users_role ALTER COLUMN id SET DEFAULT nextval('public.users_role_id_seq'::regclass);
 <   ALTER TABLE public.users_role ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    256    255    256                       2604    36695    vital_signals id    DEFAULT     t   ALTER TABLE ONLY public.vital_signals ALTER COLUMN id SET DEFAULT nextval('public.vital_signals_id_seq'::regclass);
 ?   ALTER TABLE public.vital_signals ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    275    276    276            N          0    36574 	   body_part 
   TABLE DATA           L   COPY public.body_part (id, description, name, physical_exam_id) FROM stdin;
    public          postgres    false    266   �_                0    35284    city 
   TABLE DATA           @   COPY public.city (id, capital, city_name, state_id) FROM stdin;
    public          postgres    false    216   �_                0    35291    contact_data 
   TABLE DATA           ~   COPY public.contact_data (id, additional_email, additional_phone_number, email_principal, phone_number_principal) FROM stdin;
    public          postgres    false    218   �p                 0    35298    examination_studies 
   TABLE DATA           ^   COPY public.examination_studies (id, code, name, path, registry_date, patient_id) FROM stdin;
    public          postgres    false    220   ;q      "          0    35307    familiar_surgical_history 
   TABLE DATA           t   COPY public.familiar_surgical_history (id, disease_name, family_relationship, patients_id, observation) FROM stdin;
    public          postgres    false    222   Xq      $          0    35314    function 
   TABLE DATA           K   COPY public.function (id, creation_date, description, enabled) FROM stdin;
    public          postgres    false    224   uq      %          0    35320    lab_order_laboratories 
   TABLE DATA           M   COPY public.lab_order_laboratories (lab_order_id, laboratory_id) FROM stdin;
    public          postgres    false    225   �q      '          0    35324 
   lab_orders 
   TABLE DATA           C   COPY public.lab_orders (id, date, user_id, patient_id) FROM stdin;
    public          postgres    false    227   �q      )          0    35331    laboratories 
   TABLE DATA           0   COPY public.laboratories (id, name) FROM stdin;
    public          postgres    false    229   �q      +          0    35338    location_data 
   TABLE DATA           W   COPY public.location_data (id, additional_location_info, housing, city_id) FROM stdin;
    public          postgres    false    231   �q      H          0    36089    medical_consultation 
   TABLE DATA           �   COPY public.medical_consultation (id, code, diagnosis, first_consult, reason, registry_date, treatment, patient_id, specialist_id, vital_signals_id) FROM stdin;
    public          postgres    false    260   �r      J          0    36099    medical_records 
   TABLE DATA           �   COPY public.medical_records (id, actual_disease, previous_diagnosis, previous_treatment, reason, registry_date, user_id, medical_service_id, patient_id, referred_medical_service_id, vital_signals_id) FROM stdin;
    public          postgres    false    262   �r      -          0    35362    medical_service 
   TABLE DATA           H   COPY public.medical_service (id, name, responsible_user_id) FROM stdin;
    public          postgres    false    233   �r      F          0    35667    municipality 
   TABLE DATA           G   COPY public.municipality (id, municipality_name, state_id) FROM stdin;
    public          postgres    false    258   �r      /          0    35376    parish 
   TABLE DATA           B   COPY public.parish (id, parish_name, municipality_id) FROM stdin;
    public          postgres    false    235   O~      V          0    36628    patients_data 
   TABLE DATA           }   COPY public.patients_data (id, born_location, patient_observation, patient_code, patient_type, personal_data_id) FROM stdin;
    public          postgres    false    274   G�      0          0    35389    patients_data_representative 
   TABLE DATA           d   COPY public.patients_data_representative (patients_data_id, patients_representative_id) FROM stdin;
    public          postgres    false    236   d�      2          0    35395    patients_representative 
   TABLE DATA           \   COPY public.patients_representative (id, family_relationship, personal_data_id) FROM stdin;
    public          postgres    false    238   ��      4          0    35402    personal_data 
   TABLE DATA           �   COPY public.personal_data (id, born_date, civil_state, education_level, dni, lastnames, names, profession, registry_date, sex, contact_id, location_id, occupation, nationality) FROM stdin;
    public          postgres    false    240   ��      6          0    35409    personal_surgical_history 
   TABLE DATA           {   COPY public.personal_surgical_history (id, diagnosis, disease_name, intervention_date, treatment, patients_id) FROM stdin;
    public          postgres    false    242   ϧ      P          0    36585    physical_exam 
   TABLE DATA           �   COPY public.physical_exam (id, diastolic_blood_pressure, hear_rate, height, oxygen_saturation, sistolic_blood_pressure, weigh, patient_id, physical_general_conditions_id) FROM stdin;
    public          postgres    false    268   �      R          0    36592    physical_general_condition 
   TABLE DATA           �   COPY public.physical_general_condition (id, breathing, capillary_filling, hydration, "position", state, temperature) FROM stdin;
    public          postgres    false    270   	�      8          0    35418    piscobiological_habits 
   TABLE DATA           |   COPY public.piscobiological_habits (id, frequency, frequency_rate, habit, quantity, quantity_rate, patients_id) FROM stdin;
    public          postgres    false    244   &�      :          0    35425    role 
   TABLE DATA           @   COPY public.role (id, creation_date, name, enabled) FROM stdin;
    public          postgres    false    246   C�      <          0    35432    role_function 
   TABLE DATA           A   COPY public.role_function (id, function_id, role_id) FROM stdin;
    public          postgres    false    248   ��      >          0    35439    specialist_data 
   TABLE DATA           r   COPY public.specialist_data (id, medical_college_code, mpps_code, speciality, personal_data_id, type) FROM stdin;
    public          postgres    false    250   ��      @          0    35448    state 
   TABLE DATA           @   COPY public.state (id, iso_3166_2_code, state_name) FROM stdin;
    public          postgres    false    252   ��      T          0    36599    sub_part 
   TABLE DATA           G   COPY public.sub_part (id, description, name, body_part_id) FROM stdin;
    public          postgres    false    272   �      L          0    36113    treatment_cycle 
   TABLE DATA           �   COPY public.treatment_cycle (id, cycle_number, observations, registry_date, treatment, treatment_cycle_date, treatment_rate, treatment_time, patient_id, vital_signals_id) FROM stdin;
    public          postgres    false    264   #�      B          0    35464    users 
   TABLE DATA           w   COPY public.users (id, credential_expired, enabled, expired, locked, password, username, personal_data_id) FROM stdin;
    public          postgres    false    254   @�      D          0    35475 
   users_role 
   TABLE DATA           :   COPY public.users_role (id, role_id, user_id) FROM stdin;
    public          postgres    false    256   ��      X          0    36692    vital_signals 
   TABLE DATA           �   COPY public.vital_signals (id, diastolic_blood_pressure, oxygen_saturation, respiratory_rate, rhythm_pulse, systolic_blood_pressure, temperature, medical_consultation_id, medical_record_id, treatment_cycle_id) FROM stdin;
    public          postgres    false    276   ͪ      ~           0    0    body_part_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.body_part_id_seq', 1, false);
          public          postgres    false    265                       0    0    city_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.city_id_seq', 498, true);
          public          postgres    false    215            �           0    0    contact_data_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.contact_data_id_seq', 17, true);
          public          postgres    false    217            �           0    0    examination_studies_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.examination_studies_id_seq', 1, false);
          public          postgres    false    219            �           0    0     familiar_surgical_history_id_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.familiar_surgical_history_id_seq', 1, false);
          public          postgres    false    221            �           0    0    function_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.function_id_seq', 1, false);
          public          postgres    false    223            �           0    0    lab_orders_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.lab_orders_id_seq', 1, false);
          public          postgres    false    226            �           0    0    laboratories_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.laboratories_id_seq', 1, false);
          public          postgres    false    228            �           0    0    location_data_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.location_data_id_seq', 17, true);
          public          postgres    false    230            �           0    0    medical_consultation_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.medical_consultation_id_seq', 1, false);
          public          postgres    false    259            �           0    0    medical_records_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.medical_records_id_seq', 1, false);
          public          postgres    false    261            �           0    0    medical_service_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.medical_service_id_seq', 1, false);
          public          postgres    false    232            �           0    0    municipality_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.municipality_id_seq', 335, true);
          public          postgres    false    257            �           0    0    parish_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.parish_id_seq', 1138, true);
          public          postgres    false    234            �           0    0    patients_data_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.patients_data_id_seq', 1, false);
          public          postgres    false    273            �           0    0    patients_representative_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.patients_representative_id_seq', 1, false);
          public          postgres    false    237            �           0    0    personal_data_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.personal_data_id_seq', 18, true);
          public          postgres    false    239            �           0    0     personal_surgical_history_id_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.personal_surgical_history_id_seq', 1, false);
          public          postgres    false    241            �           0    0    physical_exam_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.physical_exam_id_seq', 1, false);
          public          postgres    false    267            �           0    0 !   physical_general_condition_id_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.physical_general_condition_id_seq', 1, false);
          public          postgres    false    269            �           0    0    piscobiological_habits_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.piscobiological_habits_id_seq', 1, false);
          public          postgres    false    243            �           0    0    role_function_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.role_function_id_seq', 1, false);
          public          postgres    false    247            �           0    0    role_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.role_id_seq', 1, true);
          public          postgres    false    245            �           0    0    specialist_data_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.specialist_data_id_seq', 8, true);
          public          postgres    false    249            �           0    0    state_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.state_id_seq', 1, false);
          public          postgres    false    251            �           0    0    sub_part_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.sub_part_id_seq', 1, false);
          public          postgres    false    271            �           0    0    treatment_cycle_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.treatment_cycle_id_seq', 1, false);
          public          postgres    false    263            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 1, true);
          public          postgres    false    253            �           0    0    users_role_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.users_role_id_seq', 1, true);
          public          postgres    false    255            �           0    0    vital_signals_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.vital_signals_id_seq', 1, false);
          public          postgres    false    275            T           2606    36581    body_part body_part_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.body_part
    ADD CONSTRAINT body_part_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.body_part DROP CONSTRAINT body_part_pkey;
       public            postgres    false    266                       2606    35289    city city_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.city DROP CONSTRAINT city_pkey;
       public            postgres    false    216                       2606    35296    contact_data contact_data_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.contact_data
    ADD CONSTRAINT contact_data_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.contact_data DROP CONSTRAINT contact_data_pkey;
       public            postgres    false    218                       2606    35305 ,   examination_studies examination_studies_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.examination_studies
    ADD CONSTRAINT examination_studies_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.examination_studies DROP CONSTRAINT examination_studies_pkey;
       public            postgres    false    220                       2606    35312 8   familiar_surgical_history familiar_surgical_history_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.familiar_surgical_history
    ADD CONSTRAINT familiar_surgical_history_pkey PRIMARY KEY (id);
 b   ALTER TABLE ONLY public.familiar_surgical_history DROP CONSTRAINT familiar_surgical_history_pkey;
       public            postgres    false    222                       2606    35319    function function_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.function
    ADD CONSTRAINT function_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.function DROP CONSTRAINT function_pkey;
       public            postgres    false    224                       2606    35329    lab_orders lab_orders_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.lab_orders
    ADD CONSTRAINT lab_orders_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.lab_orders DROP CONSTRAINT lab_orders_pkey;
       public            postgres    false    227                       2606    35336    laboratories laboratories_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.laboratories
    ADD CONSTRAINT laboratories_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.laboratories DROP CONSTRAINT laboratories_pkey;
       public            postgres    false    229                       2606    35343     location_data location_data_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.location_data
    ADD CONSTRAINT location_data_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.location_data DROP CONSTRAINT location_data_pkey;
       public            postgres    false    231            F           2606    36097 .   medical_consultation medical_consultation_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.medical_consultation
    ADD CONSTRAINT medical_consultation_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.medical_consultation DROP CONSTRAINT medical_consultation_pkey;
       public            postgres    false    260            J           2606    36104 $   medical_records medical_records_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.medical_records
    ADD CONSTRAINT medical_records_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.medical_records DROP CONSTRAINT medical_records_pkey;
       public            postgres    false    262                        2606    35367 $   medical_service medical_service_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.medical_service
    ADD CONSTRAINT medical_service_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.medical_service DROP CONSTRAINT medical_service_pkey;
       public            postgres    false    233            D           2606    35672    municipality municipality_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.municipality
    ADD CONSTRAINT municipality_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.municipality DROP CONSTRAINT municipality_pkey;
       public            postgres    false    258            $           2606    35381    parish parish_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.parish
    ADD CONSTRAINT parish_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.parish DROP CONSTRAINT parish_pkey;
       public            postgres    false    235            ^           2606    36633     patients_data patients_data_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.patients_data
    ADD CONSTRAINT patients_data_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.patients_data DROP CONSTRAINT patients_data_pkey;
       public            postgres    false    274            &           2606    35393 >   patients_data_representative patients_data_representative_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.patients_data_representative
    ADD CONSTRAINT patients_data_representative_pkey PRIMARY KEY (patients_data_id, patients_representative_id);
 h   ALTER TABLE ONLY public.patients_data_representative DROP CONSTRAINT patients_data_representative_pkey;
       public            postgres    false    236    236            (           2606    35400 4   patients_representative patients_representative_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.patients_representative
    ADD CONSTRAINT patients_representative_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.patients_representative DROP CONSTRAINT patients_representative_pkey;
       public            postgres    false    238            ,           2606    35407     personal_data personal_data_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.personal_data
    ADD CONSTRAINT personal_data_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.personal_data DROP CONSTRAINT personal_data_pkey;
       public            postgres    false    240            .           2606    36730 &   personal_data personal_data_unique_dni 
   CONSTRAINT     `   ALTER TABLE ONLY public.personal_data
    ADD CONSTRAINT personal_data_unique_dni UNIQUE (dni);
 P   ALTER TABLE ONLY public.personal_data DROP CONSTRAINT personal_data_unique_dni;
       public            postgres    false    240            0           2606    35416 8   personal_surgical_history personal_surgical_history_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.personal_surgical_history
    ADD CONSTRAINT personal_surgical_history_pkey PRIMARY KEY (id);
 b   ALTER TABLE ONLY public.personal_surgical_history DROP CONSTRAINT personal_surgical_history_pkey;
       public            postgres    false    242            V           2606    36590     physical_exam physical_exam_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.physical_exam
    ADD CONSTRAINT physical_exam_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.physical_exam DROP CONSTRAINT physical_exam_pkey;
       public            postgres    false    268            Z           2606    36597 :   physical_general_condition physical_general_condition_pkey 
   CONSTRAINT     x   ALTER TABLE ONLY public.physical_general_condition
    ADD CONSTRAINT physical_general_condition_pkey PRIMARY KEY (id);
 d   ALTER TABLE ONLY public.physical_general_condition DROP CONSTRAINT physical_general_condition_pkey;
       public            postgres    false    270            2           2606    35423 2   piscobiological_habits piscobiological_habits_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.piscobiological_habits
    ADD CONSTRAINT piscobiological_habits_pkey PRIMARY KEY (id);
 \   ALTER TABLE ONLY public.piscobiological_habits DROP CONSTRAINT piscobiological_habits_pkey;
       public            postgres    false    244            6           2606    35437     role_function role_function_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.role_function
    ADD CONSTRAINT role_function_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.role_function DROP CONSTRAINT role_function_pkey;
       public            postgres    false    248            4           2606    35430    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    246            8           2606    35446 $   specialist_data specialist_data_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.specialist_data
    ADD CONSTRAINT specialist_data_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.specialist_data DROP CONSTRAINT specialist_data_pkey;
       public            postgres    false    250            <           2606    35453    state state_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.state
    ADD CONSTRAINT state_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.state DROP CONSTRAINT state_pkey;
       public            postgres    false    252            \           2606    36604    sub_part sub_part_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.sub_part
    ADD CONSTRAINT sub_part_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.sub_part DROP CONSTRAINT sub_part_pkey;
       public            postgres    false    272            P           2606    36120 $   treatment_cycle treatment_cycle_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.treatment_cycle
    ADD CONSTRAINT treatment_cycle_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.treatment_cycle DROP CONSTRAINT treatment_cycle_pkey;
       public            postgres    false    264            :           2606    35501 ,   specialist_data uk_3o3vkmyrwhjgi5eyhimx4akxs 
   CONSTRAINT     s   ALTER TABLE ONLY public.specialist_data
    ADD CONSTRAINT uk_3o3vkmyrwhjgi5eyhimx4akxs UNIQUE (personal_data_id);
 V   ALTER TABLE ONLY public.specialist_data DROP CONSTRAINT uk_3o3vkmyrwhjgi5eyhimx4akxs;
       public            postgres    false    250            L           2606    36133 ,   medical_records uk_42kriko8207e6hheasojbls2a 
   CONSTRAINT     j   ALTER TABLE ONLY public.medical_records
    ADD CONSTRAINT uk_42kriko8207e6hheasojbls2a UNIQUE (user_id);
 V   ALTER TABLE ONLY public.medical_records DROP CONSTRAINT uk_42kriko8207e6hheasojbls2a;
       public            postgres    false    262            N           2606    36135 ,   medical_records uk_8s248qjcgigmkttcom88qde1p 
   CONSTRAINT     s   ALTER TABLE ONLY public.medical_records
    ADD CONSTRAINT uk_8s248qjcgigmkttcom88qde1p UNIQUE (vital_signals_id);
 V   ALTER TABLE ONLY public.medical_records DROP CONSTRAINT uk_8s248qjcgigmkttcom88qde1p;
       public            postgres    false    262            "           2606    35493 ,   medical_service uk_9vbqauh5cw0wv8q55knowow4w 
   CONSTRAINT     v   ALTER TABLE ONLY public.medical_service
    ADD CONSTRAINT uk_9vbqauh5cw0wv8q55knowow4w UNIQUE (responsible_user_id);
 V   ALTER TABLE ONLY public.medical_service DROP CONSTRAINT uk_9vbqauh5cw0wv8q55knowow4w;
       public            postgres    false    233            >           2606    35503 "   users uk_bo820brkwh3t1qpui8w3p8rxu 
   CONSTRAINT     i   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_bo820brkwh3t1qpui8w3p8rxu UNIQUE (personal_data_id);
 L   ALTER TABLE ONLY public.users DROP CONSTRAINT uk_bo820brkwh3t1qpui8w3p8rxu;
       public            postgres    false    254            *           2606    35499 4   patients_representative uk_c0y2q0obarj6uj14vfwhx8k50 
   CONSTRAINT     {   ALTER TABLE ONLY public.patients_representative
    ADD CONSTRAINT uk_c0y2q0obarj6uj14vfwhx8k50 UNIQUE (personal_data_id);
 ^   ALTER TABLE ONLY public.patients_representative DROP CONSTRAINT uk_c0y2q0obarj6uj14vfwhx8k50;
       public            postgres    false    238            H           2606    36131 1   medical_consultation uk_cy96q9r0gembybubravt3v74h 
   CONSTRAINT     x   ALTER TABLE ONLY public.medical_consultation
    ADD CONSTRAINT uk_cy96q9r0gembybubravt3v74h UNIQUE (vital_signals_id);
 [   ALTER TABLE ONLY public.medical_consultation DROP CONSTRAINT uk_cy96q9r0gembybubravt3v74h;
       public            postgres    false    260            R           2606    36139 ,   treatment_cycle uk_imlh29ta669otfg7mybvwkqo4 
   CONSTRAINT     s   ALTER TABLE ONLY public.treatment_cycle
    ADD CONSTRAINT uk_imlh29ta669otfg7mybvwkqo4 UNIQUE (vital_signals_id);
 V   ALTER TABLE ONLY public.treatment_cycle DROP CONSTRAINT uk_imlh29ta669otfg7mybvwkqo4;
       public            postgres    false    264            `           2606    36635 *   patients_data uk_lanj780qcpj2yjn5bqycn5bie 
   CONSTRAINT     q   ALTER TABLE ONLY public.patients_data
    ADD CONSTRAINT uk_lanj780qcpj2yjn5bqycn5bie UNIQUE (personal_data_id);
 T   ALTER TABLE ONLY public.patients_data DROP CONSTRAINT uk_lanj780qcpj2yjn5bqycn5bie;
       public            postgres    false    274            X           2606    36606 *   physical_exam uk_rsgtbd6h7xf02dj6kxca8ri48 
   CONSTRAINT        ALTER TABLE ONLY public.physical_exam
    ADD CONSTRAINT uk_rsgtbd6h7xf02dj6kxca8ri48 UNIQUE (physical_general_conditions_id);
 T   ALTER TABLE ONLY public.physical_exam DROP CONSTRAINT uk_rsgtbd6h7xf02dj6kxca8ri48;
       public            postgres    false    268            @           2606    35473    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    254            B           2606    35480    users_role users_role_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.users_role
    ADD CONSTRAINT users_role_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.users_role DROP CONSTRAINT users_role_pkey;
       public            postgres    false    256            b           2606    36697     vital_signals vital_signals_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.vital_signals
    ADD CONSTRAINT vital_signals_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.vital_signals DROP CONSTRAINT vital_signals_pkey;
       public            postgres    false    276            }           2606    36185 +   medical_records fk1fn86wuax7pe4q36vxxoihh2a    FK CONSTRAINT     �   ALTER TABLE ONLY public.medical_records
    ADD CONSTRAINT fk1fn86wuax7pe4q36vxxoihh2a FOREIGN KEY (referred_medical_service_id) REFERENCES public.medical_service(id);
 U   ALTER TABLE ONLY public.medical_records DROP CONSTRAINT fk1fn86wuax7pe4q36vxxoihh2a;
       public          postgres    false    262    233    4896            u           2606    35634 +   specialist_data fk3bel52f6vidfxun3wx27rcxif    FK CONSTRAINT     �   ALTER TABLE ONLY public.specialist_data
    ADD CONSTRAINT fk3bel52f6vidfxun3wx27rcxif FOREIGN KEY (personal_data_id) REFERENCES public.personal_data(id);
 U   ALTER TABLE ONLY public.specialist_data DROP CONSTRAINT fk3bel52f6vidfxun3wx27rcxif;
       public          postgres    false    240    250    4908            z           2606    36651 0   medical_consultation fk3edwp3aekld8ejr78sqib3idr    FK CONSTRAINT     �   ALTER TABLE ONLY public.medical_consultation
    ADD CONSTRAINT fk3edwp3aekld8ejr78sqib3idr FOREIGN KEY (patient_id) REFERENCES public.patients_data(id);
 Z   ALTER TABLE ONLY public.medical_consultation DROP CONSTRAINT fk3edwp3aekld8ejr78sqib3idr;
       public          postgres    false    274    260    4958            w           2606    35649 &   users_role fk3qjq7qsiigxa82jgk0i0wuq3g    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_role
    ADD CONSTRAINT fk3qjq7qsiigxa82jgk0i0wuq3g FOREIGN KEY (role_id) REFERENCES public.role(id);
 P   ALTER TABLE ONLY public.users_role DROP CONSTRAINT fk3qjq7qsiigxa82jgk0i0wuq3g;
       public          postgres    false    4916    256    246            �           2606    36686 +   treatment_cycle fk3v6aq009rvagf2u7xhq2j90dp    FK CONSTRAINT     �   ALTER TABLE ONLY public.treatment_cycle
    ADD CONSTRAINT fk3v6aq009rvagf2u7xhq2j90dp FOREIGN KEY (patient_id) REFERENCES public.patients_data(id);
 U   ALTER TABLE ONLY public.treatment_cycle DROP CONSTRAINT fk3v6aq009rvagf2u7xhq2j90dp;
       public          postgres    false    264    274    4958            ~           2606    36175 +   medical_records fk4531w4l7luvnd12cpms8eaxiw    FK CONSTRAINT     �   ALTER TABLE ONLY public.medical_records
    ADD CONSTRAINT fk4531w4l7luvnd12cpms8eaxiw FOREIGN KEY (medical_service_id) REFERENCES public.medical_service(id);
 U   ALTER TABLE ONLY public.medical_records DROP CONSTRAINT fk4531w4l7luvnd12cpms8eaxiw;
       public          postgres    false    233    262    4896            f           2606    35524 2   lab_order_laboratories fk4xnu2f6xkpea41jgyf35ivgu2    FK CONSTRAINT     �   ALTER TABLE ONLY public.lab_order_laboratories
    ADD CONSTRAINT fk4xnu2f6xkpea41jgyf35ivgu2 FOREIGN KEY (lab_order_id) REFERENCES public.lab_orders(id);
 \   ALTER TABLE ONLY public.lab_order_laboratories DROP CONSTRAINT fk4xnu2f6xkpea41jgyf35ivgu2;
       public          postgres    false    225    4890    227            o           2606    35609 )   personal_data fk5psyxuj0pa2db2rekk9sw6eh6    FK CONSTRAINT     �   ALTER TABLE ONLY public.personal_data
    ADD CONSTRAINT fk5psyxuj0pa2db2rekk9sw6eh6 FOREIGN KEY (location_id) REFERENCES public.location_data(id);
 S   ALTER TABLE ONLY public.personal_data DROP CONSTRAINT fk5psyxuj0pa2db2rekk9sw6eh6;
       public          postgres    false    240    231    4894            r           2606    36681 2   piscobiological_habits fk60mrsh55h53m5ex4eq6q9ygol    FK CONSTRAINT     �   ALTER TABLE ONLY public.piscobiological_habits
    ADD CONSTRAINT fk60mrsh55h53m5ex4eq6q9ygol FOREIGN KEY (patients_id) REFERENCES public.patients_data(id);
 \   ALTER TABLE ONLY public.piscobiological_habits DROP CONSTRAINT fk60mrsh55h53m5ex4eq6q9ygol;
       public          postgres    false    244    4958    274            c           2606    35504     city fk6p2u50v8fg2y0js6djc6xanit    FK CONSTRAINT     �   ALTER TABLE ONLY public.city
    ADD CONSTRAINT fk6p2u50v8fg2y0js6djc6xanit FOREIGN KEY (state_id) REFERENCES public.state(id);
 J   ALTER TABLE ONLY public.city DROP CONSTRAINT fk6p2u50v8fg2y0js6djc6xanit;
       public          postgres    false    252    4924    216            h           2606    36646 &   lab_orders fk7aibn2vi2qg3uhm4d8sjhuqp0    FK CONSTRAINT     �   ALTER TABLE ONLY public.lab_orders
    ADD CONSTRAINT fk7aibn2vi2qg3uhm4d8sjhuqp0 FOREIGN KEY (patient_id) REFERENCES public.patients_data(id);
 P   ALTER TABLE ONLY public.lab_orders DROP CONSTRAINT fk7aibn2vi2qg3uhm4d8sjhuqp0;
       public          postgres    false    4958    274    227            e           2606    36641 5   familiar_surgical_history fk7s0lcgc72nld5ag7q6tp7glxe    FK CONSTRAINT     �   ALTER TABLE ONLY public.familiar_surgical_history
    ADD CONSTRAINT fk7s0lcgc72nld5ag7q6tp7glxe FOREIGN KEY (patients_id) REFERENCES public.patients_data(id);
 _   ALTER TABLE ONLY public.familiar_surgical_history DROP CONSTRAINT fk7s0lcgc72nld5ag7q6tp7glxe;
       public          postgres    false    222    4958    274            g           2606    35519 2   lab_order_laboratories fk8u49y0bdurf5jt246mocrm904    FK CONSTRAINT     �   ALTER TABLE ONLY public.lab_order_laboratories
    ADD CONSTRAINT fk8u49y0bdurf5jt246mocrm904 FOREIGN KEY (laboratory_id) REFERENCES public.laboratories(id);
 \   ALTER TABLE ONLY public.lab_order_laboratories DROP CONSTRAINT fk8u49y0bdurf5jt246mocrm904;
       public          postgres    false    4892    225    229            l           2606    36666 8   patients_data_representative fkbcjbhl65sebpdc4nbw8lk7dfp    FK CONSTRAINT     �   ALTER TABLE ONLY public.patients_data_representative
    ADD CONSTRAINT fkbcjbhl65sebpdc4nbw8lk7dfp FOREIGN KEY (patients_data_id) REFERENCES public.patients_data(id);
 b   ALTER TABLE ONLY public.patients_data_representative DROP CONSTRAINT fkbcjbhl65sebpdc4nbw8lk7dfp;
       public          postgres    false    274    236    4958            p           2606    35604 )   personal_data fkbh47ta6aole8lr58s4loh2ea3    FK CONSTRAINT     �   ALTER TABLE ONLY public.personal_data
    ADD CONSTRAINT fkbh47ta6aole8lr58s4loh2ea3 FOREIGN KEY (contact_id) REFERENCES public.contact_data(id);
 S   ALTER TABLE ONLY public.personal_data DROP CONSTRAINT fkbh47ta6aole8lr58s4loh2ea3;
       public          postgres    false    218    4882    240            d           2606    36636 /   examination_studies fkcybbebvfxn01pqiscf9k0g8ch    FK CONSTRAINT     �   ALTER TABLE ONLY public.examination_studies
    ADD CONSTRAINT fkcybbebvfxn01pqiscf9k0g8ch FOREIGN KEY (patient_id) REFERENCES public.patients_data(id);
 Y   ALTER TABLE ONLY public.examination_studies DROP CONSTRAINT fkcybbebvfxn01pqiscf9k0g8ch;
       public          postgres    false    4958    220    274                       2606    36170 +   medical_records fkdd9qwwn228b1yaci52j69dkp4    FK CONSTRAINT     �   ALTER TABLE ONLY public.medical_records
    ADD CONSTRAINT fkdd9qwwn228b1yaci52j69dkp4 FOREIGN KEY (user_id) REFERENCES public.users(id);
 U   ALTER TABLE ONLY public.medical_records DROP CONSTRAINT fkdd9qwwn228b1yaci52j69dkp4;
       public          postgres    false    262    254    4928            y           2606    35673 (   municipality fkddpbdyfcgxidlnmw7mk2fhyh3    FK CONSTRAINT     �   ALTER TABLE ONLY public.municipality
    ADD CONSTRAINT fkddpbdyfcgxidlnmw7mk2fhyh3 FOREIGN KEY (state_id) REFERENCES public.state(id);
 R   ALTER TABLE ONLY public.municipality DROP CONSTRAINT fkddpbdyfcgxidlnmw7mk2fhyh3;
       public          postgres    false    4924    252    258            �           2606    36723 )   vital_signals fkemorekop6pu5fbov30le1d6wl    FK CONSTRAINT     �   ALTER TABLE ONLY public.vital_signals
    ADD CONSTRAINT fkemorekop6pu5fbov30le1d6wl FOREIGN KEY (treatment_cycle_id) REFERENCES public.treatment_cycle(id);
 S   ALTER TABLE ONLY public.vital_signals DROP CONSTRAINT fkemorekop6pu5fbov30le1d6wl;
       public          postgres    false    264    4944    276            s           2606    35629 )   role_function fkeqk2i5h6orbp5blnkcxu880tk    FK CONSTRAINT     �   ALTER TABLE ONLY public.role_function
    ADD CONSTRAINT fkeqk2i5h6orbp5blnkcxu880tk FOREIGN KEY (role_id) REFERENCES public.role(id);
 S   ALTER TABLE ONLY public.role_function DROP CONSTRAINT fkeqk2i5h6orbp5blnkcxu880tk;
       public          postgres    false    246    248    4916            i           2606    35534 )   location_data fkh8dhbvld8g4ekj5frnn7a0jfa    FK CONSTRAINT     �   ALTER TABLE ONLY public.location_data
    ADD CONSTRAINT fkh8dhbvld8g4ekj5frnn7a0jfa FOREIGN KEY (city_id) REFERENCES public.city(id);
 S   ALTER TABLE ONLY public.location_data DROP CONSTRAINT fkh8dhbvld8g4ekj5frnn7a0jfa;
       public          postgres    false    4880    231    216            {           2606    36698 0   medical_consultation fkhkjr6xn6pskvtta5om86axmjt    FK CONSTRAINT     �   ALTER TABLE ONLY public.medical_consultation
    ADD CONSTRAINT fkhkjr6xn6pskvtta5om86axmjt FOREIGN KEY (vital_signals_id) REFERENCES public.vital_signals(id);
 Z   ALTER TABLE ONLY public.medical_consultation DROP CONSTRAINT fkhkjr6xn6pskvtta5om86axmjt;
       public          postgres    false    260    4962    276            �           2606    36718 )   vital_signals fkhrmemme5dt84gqjvq3ogbesj8    FK CONSTRAINT     �   ALTER TABLE ONLY public.vital_signals
    ADD CONSTRAINT fkhrmemme5dt84gqjvq3ogbesj8 FOREIGN KEY (medical_record_id) REFERENCES public.medical_records(id);
 S   ALTER TABLE ONLY public.vital_signals DROP CONSTRAINT fkhrmemme5dt84gqjvq3ogbesj8;
       public          postgres    false    4938    276    262            �           2606    36617 )   physical_exam fkhwqluc3yxd9qms5wdsjhk7h4g    FK CONSTRAINT     �   ALTER TABLE ONLY public.physical_exam
    ADD CONSTRAINT fkhwqluc3yxd9qms5wdsjhk7h4g FOREIGN KEY (physical_general_conditions_id) REFERENCES public.physical_general_condition(id);
 S   ALTER TABLE ONLY public.physical_exam DROP CONSTRAINT fkhwqluc3yxd9qms5wdsjhk7h4g;
       public          postgres    false    268    270    4954            �           2606    36676 (   physical_exam fkji148v46v5bkvv8macpbcqgy    FK CONSTRAINT     �   ALTER TABLE ONLY public.physical_exam
    ADD CONSTRAINT fkji148v46v5bkvv8macpbcqgy FOREIGN KEY (patient_id) REFERENCES public.patients_data(id);
 R   ALTER TABLE ONLY public.physical_exam DROP CONSTRAINT fkji148v46v5bkvv8macpbcqgy;
       public          postgres    false    268    4958    274            �           2606    36656 +   medical_records fkk6qip0ox735fc4rlnmt1qcg0g    FK CONSTRAINT     �   ALTER TABLE ONLY public.medical_records
    ADD CONSTRAINT fkk6qip0ox735fc4rlnmt1qcg0g FOREIGN KEY (patient_id) REFERENCES public.patients_data(id);
 U   ALTER TABLE ONLY public.medical_records DROP CONSTRAINT fkk6qip0ox735fc4rlnmt1qcg0g;
       public          postgres    false    274    4958    262            v           2606    35644 !   users fkl5eerfuv1ty0tw5eguaop17k5    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkl5eerfuv1ty0tw5eguaop17k5 FOREIGN KEY (personal_data_id) REFERENCES public.personal_data(id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fkl5eerfuv1ty0tw5eguaop17k5;
       public          postgres    false    240    254    4908            k           2606    35678 "   parish fklat5r241l5e5r938edfkanwr3    FK CONSTRAINT     �   ALTER TABLE ONLY public.parish
    ADD CONSTRAINT fklat5r241l5e5r938edfkanwr3 FOREIGN KEY (municipality_id) REFERENCES public.municipality(id);
 L   ALTER TABLE ONLY public.parish DROP CONSTRAINT fklat5r241l5e5r938edfkanwr3;
       public          postgres    false    4932    235    258            �           2606    36661 )   patients_data fklb3jtm77qie66vru97qxvl2yf    FK CONSTRAINT     �   ALTER TABLE ONLY public.patients_data
    ADD CONSTRAINT fklb3jtm77qie66vru97qxvl2yf FOREIGN KEY (personal_data_id) REFERENCES public.personal_data(id);
 S   ALTER TABLE ONLY public.patients_data DROP CONSTRAINT fklb3jtm77qie66vru97qxvl2yf;
       public          postgres    false    4908    240    274            �           2606    36703 +   medical_records fklha6lc6659hh3cqk2j33m7u9f    FK CONSTRAINT     �   ALTER TABLE ONLY public.medical_records
    ADD CONSTRAINT fklha6lc6659hh3cqk2j33m7u9f FOREIGN KEY (vital_signals_id) REFERENCES public.vital_signals(id);
 U   ALTER TABLE ONLY public.medical_records DROP CONSTRAINT fklha6lc6659hh3cqk2j33m7u9f;
       public          postgres    false    4962    262    276            �           2606    36607 %   body_part fkmh8wytyqttxfmtlyqbsqkvd1h    FK CONSTRAINT     �   ALTER TABLE ONLY public.body_part
    ADD CONSTRAINT fkmh8wytyqttxfmtlyqbsqkvd1h FOREIGN KEY (physical_exam_id) REFERENCES public.physical_exam(id);
 O   ALTER TABLE ONLY public.body_part DROP CONSTRAINT fkmh8wytyqttxfmtlyqbsqkvd1h;
       public          postgres    false    268    4950    266            m           2606    35589 8   patients_data_representative fkna415qfmgb5x2ghwon7ap59xa    FK CONSTRAINT     �   ALTER TABLE ONLY public.patients_data_representative
    ADD CONSTRAINT fkna415qfmgb5x2ghwon7ap59xa FOREIGN KEY (patients_representative_id) REFERENCES public.patients_representative(id);
 b   ALTER TABLE ONLY public.patients_data_representative DROP CONSTRAINT fkna415qfmgb5x2ghwon7ap59xa;
       public          postgres    false    236    4904    238            n           2606    35599 3   patients_representative fknhq47692cu9cc32twsej0qko1    FK CONSTRAINT     �   ALTER TABLE ONLY public.patients_representative
    ADD CONSTRAINT fknhq47692cu9cc32twsej0qko1 FOREIGN KEY (personal_data_id) REFERENCES public.personal_data(id);
 ]   ALTER TABLE ONLY public.patients_representative DROP CONSTRAINT fknhq47692cu9cc32twsej0qko1;
       public          postgres    false    238    4908    240            �           2606    36708 +   treatment_cycle fknvyrtgdjcmlwtq9m5apnpy5eo    FK CONSTRAINT     �   ALTER TABLE ONLY public.treatment_cycle
    ADD CONSTRAINT fknvyrtgdjcmlwtq9m5apnpy5eo FOREIGN KEY (vital_signals_id) REFERENCES public.vital_signals(id);
 U   ALTER TABLE ONLY public.treatment_cycle DROP CONSTRAINT fknvyrtgdjcmlwtq9m5apnpy5eo;
       public          postgres    false    276    264    4962            |           2606    36160 0   medical_consultation fko7xk88v2tlut3gh5r42ar65c3    FK CONSTRAINT     �   ALTER TABLE ONLY public.medical_consultation
    ADD CONSTRAINT fko7xk88v2tlut3gh5r42ar65c3 FOREIGN KEY (specialist_id) REFERENCES public.specialist_data(id);
 Z   ALTER TABLE ONLY public.medical_consultation DROP CONSTRAINT fko7xk88v2tlut3gh5r42ar65c3;
       public          postgres    false    250    260    4920            �           2606    36713 )   vital_signals fkpdoavmi07cijbqng46r173bsv    FK CONSTRAINT     �   ALTER TABLE ONLY public.vital_signals
    ADD CONSTRAINT fkpdoavmi07cijbqng46r173bsv FOREIGN KEY (medical_consultation_id) REFERENCES public.medical_consultation(id);
 S   ALTER TABLE ONLY public.vital_signals DROP CONSTRAINT fkpdoavmi07cijbqng46r173bsv;
       public          postgres    false    4934    260    276            t           2606    35624 )   role_function fkpxk51k07j2jbxi2vckjesc9io    FK CONSTRAINT     �   ALTER TABLE ONLY public.role_function
    ADD CONSTRAINT fkpxk51k07j2jbxi2vckjesc9io FOREIGN KEY (function_id) REFERENCES public.function(id);
 S   ALTER TABLE ONLY public.role_function DROP CONSTRAINT fkpxk51k07j2jbxi2vckjesc9io;
       public          postgres    false    224    248    4888            x           2606    35654 &   users_role fkqpe36jsen4rslwfx5i6dj2fy8    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_role
    ADD CONSTRAINT fkqpe36jsen4rslwfx5i6dj2fy8 FOREIGN KEY (user_id) REFERENCES public.users(id);
 P   ALTER TABLE ONLY public.users_role DROP CONSTRAINT fkqpe36jsen4rslwfx5i6dj2fy8;
       public          postgres    false    254    4928    256            q           2606    36671 5   personal_surgical_history fkqtfn53ba68pg6gql6orm8fj8m    FK CONSTRAINT     �   ALTER TABLE ONLY public.personal_surgical_history
    ADD CONSTRAINT fkqtfn53ba68pg6gql6orm8fj8m FOREIGN KEY (patients_id) REFERENCES public.patients_data(id);
 _   ALTER TABLE ONLY public.personal_surgical_history DROP CONSTRAINT fkqtfn53ba68pg6gql6orm8fj8m;
       public          postgres    false    4958    242    274            �           2606    36622 $   sub_part fkrn065t8q2l7r0qhmfk89viw7c    FK CONSTRAINT     �   ALTER TABLE ONLY public.sub_part
    ADD CONSTRAINT fkrn065t8q2l7r0qhmfk89viw7c FOREIGN KEY (body_part_id) REFERENCES public.body_part(id);
 N   ALTER TABLE ONLY public.sub_part DROP CONSTRAINT fkrn065t8q2l7r0qhmfk89viw7c;
       public          postgres    false    272    4948    266            j           2606    35569 *   medical_service fkxm2d97n76u1q02elu178sjls    FK CONSTRAINT     �   ALTER TABLE ONLY public.medical_service
    ADD CONSTRAINT fkxm2d97n76u1q02elu178sjls FOREIGN KEY (responsible_user_id) REFERENCES public.users(id);
 T   ALTER TABLE ONLY public.medical_service DROP CONSTRAINT fkxm2d97n76u1q02elu178sjls;
       public          postgres    false    254    4928    233            N      x������ � �            x�mZ�r�]S_�/�����(�K�[v�Sw���p��̸��7^z�Eʻl�c�����!o\�4�O?���W�������a��ݷ>6U���-��S%h.3>���c��U�_��e�n�ё�^�nY��c�?���aΕ�K_T�����|,Oe�U��O˧��3�]ٸ6b�b�b(}-b6�����.F�^�)t�d���k�c02ԑ��ȥ�� ���b㿶�t[N��৳�H�~����� 6����Sl�^6&'�+�`���Y�q���"o]�:�;��=�;�z��e���\�Ϗ7��[��6�Pm��Lh��䯜}0��o^�ͺ�UUq֏U�Wn%b��~�a��G����W��q���L���9wb��3q�+�R�
�c��ˍ\t`������)�*�(����|��hN�.�䂣�����LL�Yo[Q�M�o��w�.��Fܹ��x9��b4���� �)l\� �����U����_W����|u��,�*k���� b9�ò�������U�����r�/��.,�,z}��ݾ_�y#0ƪn�ވF,���?F~�����a�[! ���G�pu`����NUFS�gy��U����	��ƍ�/Lh2�(h��p}��?[ǝNr%�Pʙ����@(C��X��ʥT�+V>�L����/�_AH5��_UE�**tn� �^�Ӥ��p��)6|bM��mm���o��4��r��&6���c��v�����'��2QF{�����ta� NE'��_`I��>(�JT��fƣo��V�E�a��.2�;��d�Y��k %���O�t�i �Li����Z����Ǖ��� ��^��	2t��E�[�х��^b4����4ǎ?�b���O[X�4��M/�_`�T��-��Zv���b�ʩH����NB��V8�0F	c���_'&��)���7M9�,�z����b �B�8���mU�Y�+�&�CZ���+����J�X,ીȎ�"2���]�Ύ�E�k��E�Mg+V��v�����������St]j,����
L��!
�4
aϗ�`Q�o&2�|)�/&$Qg��J� �͙c�z��U �`+�(��w�= 3�_�����۰���L�x}FV��H2 �բe�n�-�����3Z8^��ත��l!���+�@�������)8�M�`��.UE��yđ��\�q
�ݢH��e"�� �0XH�5��۶TU��W�Q�$�ɜG��%SG��a�Jy���V.!�!��aX�g#u!�=��b�
�
��؊���� �KU��2�-������4{ۮ�+"F�0� �7�Nv�E��[�9ރhX�X��*��Q�|�P{'Y�i����o�`X���(�S(��XVؿq�f�cK�y�Z��:�y�.&��0"��J�'���LX���d�&�p8%��m0d�;�'��3�&o$ڵ 9=%#3r��7�26�>���c3����I ��B����/ɶ���РxE�ɰ�dl�" ��Bh}gSr��4�'��hx����5ǧAl���"�N�"��=�I����Z����~���j����}l�/;������wK��p���4�����������r�\P���óÕJ0����oi��Mu�lk��Q��x_/[['�S��3y��.L���IEEbi,����LO�G2P- �H7��ͪC�#	
2o�] ��L2iג�S��߰��8��܌bƄj�
�5dG0!�!��o�F�0JV4g��[<Tr��P �8\5m�)��YUT ���.\�d)�~88To���﵂���n�@�
�c���!�����#����2u.�,⢆1Z�B��V����I����4�-�b�hU�Oz@����ִ7���J怊�Ш�r��hČ��Z6U�c[��8=���	�^k���j���[�D�/vV&��^�\"SCb��q��
��m�׈������I�f����2��ޫ�2�
�`��J��M(mMfN�A�G�
aS5�;�i)ZW�MQ�N��3�Ae�O��bz��2Dy8ưqQ���@�3�MI,��#���^1�V}i�Ԯ�zk2�j��I��¢�L�%m��	0��H�f	��ȷrMȫ�a�7��05g�Hn�Q��J�!���C�/no��r����C�fL8�&���C1/��>��?����K�s�~�'3`)�_����JQ\f_���&�ǟ��N�Q�X�)!�ߠ<��k|qi����E������'��C����7�|�]�:���)!kCYT������Ƈ��w���4S�����3�g"� \�ѽ2u@��\�X�I�a*5rQVq�-���8�pX���Up��\�-��E��L���x[����+!m�2�^�iō�V,�%e�o��o�fRCࡪ��M٦:�2s��_��`�.������!��ށe��d�y���a)��?9��u�Fq^��,�"W�f�-��}���7ӓ���M���=SO��#f4	�Ro��+3J�G�V ��,�q�8Fx 9/Õ�ޫ��"Q�n������]�L4���Q )�"����$䨗#�����\�}焮e$���lCw�uY.��+�6���-v�~]�}1v�D�rk�U!��9�Pm6��M�^�z:�T���i����)8U_ey�w*��h����gUVt3�Rob[I�fϖ�9�����DD�Ύ���ТIi)��e<[D�<��Y�lr��\��ZCa�/0.[S3���>�p7dML�F>��X!��f����H�N"�p��C�aE��;(����-�B�����v>���F��� N���
!����n�DR>�E��F(4P/��H�J �#2wb�77o�p�I&16����ٳ��֜Ph�S�����D��a�:;�H�2`���b��qЪ?��P�pU��zwH/���;:��|���������V��T�Z�3_4���� ����b"s��kMT炨�HN�(3}�� T�*k�u|��PU��B�{���n!�U\14����Z`�@&�ɴ@x!R�n��Þ%�z����ͯ1؞�����aV�<�Y�2L������N5���o�G��ɏ���K��tkj7Ih�`�H��؄4�D���tz&��D�g�p�R&���'$��SZu�u%�0��Iu6{eZ��	��a�w���1vh"�ɑR����K��&���]�	6'��i圦&�����23�+	k�a8�-߄u��Ih��|�Ck28Z�H�3�S!�}���o��$k7�e�T)�bWbI����ﵐ��_ #�o�Z73�a]{������������خQ�pJnq���/uٮ�������lM,b6��Vjd�T���T�א/���#��#��/E}�h�.�dK5: K�Gi:e���Z��o;p��.��"$e0�KN���
�=~2G��E����"��ݙ d�!j  C�}�^�a��pR2��P���e[,h�"��iZA ?��\r�28���_����ЎDIIWt�z[|��T��ƾ�8��2��N_� ۃDR�=�� ST��z�Z�X��ӄ����8�|b�Lh̀�~�1����Z-�`��^��kN��Z�q
��בS�P��\�a�}���#�b!ǯ�l/�5�E։�r�&Qr
��F�c���_�ds���Hz�wq����j+��Qs�����˭/���|�[��)��X��^U��%�8u���И����p<���?�"�y�S��S�B���#c#A��]�t�Ize�:�6�cX�"��(!i�P�n�)�,I}P���C0M�7�<�R���MO4����6��N#�,��s��Y2<�Q%����Y�WIM̒�yrk:�bkW-)|S5��k�bk�~#�]�[1��}[~���o��?Uxsr����u"��L_���ɗ�̇d�lH޵q��!@�~Q���v�"P�<��H�"=��
[q$`�v���6v��>�/[\)d읃;7� ��Oi t   \B��h|6O������g��[I�@�s�n��+���f��}m��Mx���ɘ\~(�b��b�Ŵ������˯��c?����P���V�}q����$a��AK���������qD�         �   x����
�0�s��$���7d��D;�"{���`x�1>��'���6Ʋ�SiƲ��<=ʌq�WM�fB\c' W.��$�#��i�mSB����>wĞ=u�Λe�Md� O-Ǣ9)Gg�B}���V�0Z!�Y�Y�Y�f)Vٞ��w}ț�57��ջ5ι7��J             x������ � �      "      x������ � �      $      x������ � �      %      x������ � �      '      x������ � �      )      x������ � �      +   �   x���A
�0��ur�9�H'i�.{Wna�6%��QZ�Y	o5�����\g����>Ь%�3CЗ>5QP��q�b�����S���潎o
i�IBʴ�g\P�,,<,ZXp%������Y4m����:����k������Z��v��      H      x������ � �      J      x������ � �      -      x������ � �      F   ]  x�mX�v�<���`���)RK�I��ı�?)��D��C�
H�l����L�"�]Z���; i�Ic��P ���s�XU]�;[7e��Y��:��{>/��;��1UG}�k��L��ao�3@K �О��ᩉ>���W��2����m������T���x8v�C�+:c�ƚm뾳m���ֵ��_&�q��7Mta���7���z�t]ڶl�7�D�����^��qSujj�t]Y��w�Yy�0�����^ם̳T�����b��Ǧ�G���ΚZouK�J}�O���kjx���V�:.�'|�[YN2W��ڸNo���oLtf�6�6Iԅ�8̦��w���p�d�.�'g�� UW�Ư�K�^ȼ����1k;���%K���a���nx�����5�%Y���f�%l{�@3�Y�2��Z�sul]�KM��~�1<?`�ã��E���3w-)��y�WMt��
�*�����e�|�u��=�ָMөt��մP������$,������mtT��#>��t":���"8����8���+���Ү�=	�K��������[i1��RҔ���;["M��r�q���:/���xT::i��x>G��/A�&�vg�%˕�::��m%����a1	��X�Tޚ��\^6WW}�B�c���_Y�n����;���5�1���4(�{��\c�FWk��Y����	���m�|��+�rC�
`�C?/|9�߶�ɍ%\�c,%(^͖su2�.�AC�2VO�5��m����3K?�I����KdRӊq��pM=<��l����-X��,̪�u�٠?l�r���{C��c�.@x|A��V���q���H�O�'�,�--��Q����� Y��Bo��$n��iFJ�;d��g9�U��ME �k�_Z��Z�� �l������09�������F�� �c�5�x�>��j�*�\]��'zm*Y�JR1������~x� �\}ѕ'R�{������V�fE���
0�^��ȁR,��4�!��	KN�6�
z��Җ�w�!�'XW�¸�ą,@F���)�߀pT[�H�x,�L~�W�/����T��U<�^�[�R�孽ǀ�$�.��ްQ`�ƛ%����b)�ISـ�G� M�z�Zb�N�kJ���bA�����C�I��ɔ���D{����E `�e��Υ$�5"E5@K��]�
t�1�L��e��g���BW ۦ
��l�A2�5��F.��4����oܛ�hk7���j75�t5��B�ƶg��1��Gښ�n ��qe�8-ɟ:�x�s�7�E"�+��yj��E�#ąm�+2{t��B�z36\�ʍ+�
Ŗ+�#�P��7޾
��/-<(�B��nxz�vw����]d�7dP�_����}IP
A���ao]p"DSo��(d��#�6�@�������p�D1@{A�|��##�'!#*�N�ޔ��ba��墢l�F�>'B����Y���q�I�����c�;1�E\X<��*�e�L��J�K���^�׾�H�z0�7�?�X�Db��.���y�3�0�t1HR����Z2���]��2=^IBaB�\;.p�����^��X��a�K�R̄c���ف�ORC�-B+��@�Ɣ�b���TQl�-S��R�#2�=����xo��|0ڡ#���띠 ]_˗�3k*����w%m�ed�,rTB�q#S��uXi�(Jz&X�y!B_�=x �L:�<.���ÐA u���y)"v����Ē0j��
�i���!F�@D'A��0sny4� th�@�x0�H�}X2�����O��~1�������Ɔ���x0��Bѽ�KK[���I�	\�&�Ĝ�������B�!x�ř<S�ԥ�6���^��#��*�F�w�|K&�5�e��D��#;-�I���W�+Q��	��p�A�P���r)A��Q�d��Q붯�cϐU�N�}�n� �L_�v���E�H$�b��a"yiA���m��gR�ï�� m�xC~��}��� q�7B|m��۽�0Q1�%@���7�Tq�f�Vq�[7�S����3��x:��ѵ�DՕ�;��& �&@n|s-��yVp�餽muapBa\:~�
�]d4����h�R�I���L���I���j���Ԧ%{�
B䌹��$�H�<!h]�� Ģ�7�w��4~My����F�|�F'2��ay^N>��
��v�z���e��r[�>�}A�aV��_1�=�ͽ������'��O�f��'i�,��<W�aTcA�|�n֚Q��BD��m���/1����I#����b�
�5�k}y���������ʻbR��Vif��xU�?�/���&���K�֔��q}���pe#��9����� E�Ps��$��~�[/fŜ��C�Ff?�wx�0y���~��
9��%�2��x���ʞ�E�rTצB��ΐd�?�ty��r/�G"�nG9%o��8����D!(TJ������5��.�8��'�}؅��1�Aۈ1{E^/��a�0��Ύ��Z�4Z����Gr��$Nb �4�N��N�|�+��0�,mLw���0<�4�����'��xA6�]asq���kgk̸�d��Ŧn��8p~�Nn}��	� ��|���L�B�K7���$4��bƋ��h��צ$�j��"��l�*Y��P��󈙸�<�	d"��n�xC��5;���Q}��ָ袯��Q����`u�~"�j'yh4�	�(Fg��+�ۻ᧟��$En�}�S���Fo��_�W�q��1<���0����}v��K�������@rS�.yM%ה�G�_��8j�K;.������x�( f�Bƙ_�����Z*Ig���l6�?}jP      /      x��|Ms�H���\v/��,%�,�²�l�c�b6 ���&5HhF��O��/:jW�;��wι� H�"ޢ��s`"������;��뛶[uA8��WC��Rm�U���U�V�U���M�W�]������8��N����@��$>6u�W��U�l�?��7в��U���o�;���UA4˃_���?�w��~�p�Y\����ϷÖ]��eݷU����M��ŀ�>�g�"x34���fY���������0x_}j�?��3�c2�=����j�ͦ��'��}S�wA2|ܽ�;n�v=�V`������~��=����n�us���euwE��s��w\� �Eap�m��YWk6#�ʮ���`��|� ��G���U����7ݎ���𽛿�o��T,J��Z��m�I.fQ��~���-���K��(gQ\���P�}�U��7][�c��՗�?|Ǌ�C�������clbF-������A��X�u���F F<���;>W�M��4��=�xg|�ry��/��s���0�~/��E���r��V�C��=ؒ�d�����0�%��/mM���x0�%1��愳E��o8�|���{̼��#��hX@l._4�m�ϸ����oVF,�_jLE�V�6T{Q҅��7xY?<a1K����۩�ڎϖ�Ԧ�}��X�������6�Y�jv*��~���÷��<�O}�u�@�.��L~h�-��V\v쇪#�-�^���_[dL�ʀ���Yon�j��7���z��/='�g��(�e6�/��]5;�`�����gl� Jf�8��/�[�?����r�� I@4�Tc��o��/�fy�ƅ�v\��- n�(��	>,[���v[1Ӝ4`5��-fe5@r�i���w����ҁe���o��Q1+����}�w��\KB\@&��ѯ�Qpq?�Ң��%u�vBGw.��O+�vg���]�W;-��H�<�lz<	i��Ep	6�;�pV���v_��Y.���� ��M��P}�ᛄiJ�b�j�����ARn8�r<�`�,�55��{�]�3'˶�ƀܲ��{�M�����8����ݺzzj�
��jh���� ʩ�Bg�Eč�	�V�U��.�e�Bz�ptj�*@p�l����~�~�0�6Ӷ"w�����j��n�HE�e/���A�
.�a��Pͯ�u�(�M����N��寍[�:�e�u��>Ƿ>�/!�W�^h�7P������W�/�M�j*��c��[;�~��K��hg�[H0�� ��-��(�~�7\�4����zӠ�6T�M���v=@�i�Ȼ���uC]�>�����H�����ۦ�������T�P��M�P�)�7s��3�X�J������	k5r�kn�?���-hh�x��а(�l�)NĪT:;c	T3I��%zE�J�t��d8��
�O/����w;��t*m�m��ZA�^�Q��v��1�3��˾㧑�9��My'����ӈQW@��԰֗����i��ڋ��ۜ(T�՗��.	W
:Y��A�����! �CV���"������M��`ᡇ?5��j��)�^
�&���8�Ay`va�%����#�2A�r���%�A	��?WF�O��GkE�o�H�"���o����<����}gTg�h1>A8޺'a���~W�Ms=�n�4:!2(�.0I-����w莧��@�[�i=K�V�e��-&���������!���E��UwI�����hohi�6����(q�L�"��d|
�d ���������m4>&�ݧD�#{@�{���}�	Z�R��xڳ���p�HP�7Ê�00X���u��zY�>ŀ���Z@���(�}��'�Z�\�����J.��[X�X��÷v��!�4'a�}4�v�ٵ�/�)��hR����g�( �@4���7Z�����ۤ�cC�r�P�n{��_W��4n�͚f��3>L��a���?�9.�N�D��^Hyn�gؚ�&L3^�j���G��fOT/�=Q�*Ť���ݲ�B��kK�F��{����aר���@���%msS{Ƌ�Xg���m��G��v�M��hY�6�
F	�[�xj)I�sf��1b�����;��ٗ�B<���}��+�+(�8��R��b����Lod��/�ͨ��9YI7],�fc ���n���t]Հ�6��v���B��Wd(������~�,�_�m���Rm<H�N,��zmF����V�@	�`��(z0��9-$�Xmz�j'R`��=K'���S��LT`Z�\h	�=�Pe���m��h��?��iރ��~�~�"�����0�A5=��Mo�$�3_̫�mLo�#�e����諺ߒ���5��?�Y����]�Њ�C�J�k��c�@���1� � ��Ps	��e������`p|�c�@�� |�c���F�.ȱ��,)���k0�DJk�����4�:k�\p�{t>D2n���`
A��S�r=�#i��y��@I�`<�w�]��`VA4O�������vղ�f�l�.e���j
r0�6��e��ئE�mx_���rp�4�`G�lj�)�����
�4�̀�5�@�5�!�s�e�zv$6J����H	_`ɠ�_�����L`(���1l��m�ͽ�sb��%	�z�-��_�B�����[l�	�	*�����k!9�x���	*��|W=�>�)Q'�M� ��l���]��DXt(~�kj��xOP0r���0�R���W'(1�Ev��j�hO!���4���1ڲ�K�[�9w��q��6���}�Ȟ��� 2��-t���o��8`�B舖�&l�-��\�.) �KL�\{MZ�sr㚢�Lf�6Ί�R�BW*S��I$�� #I�L�(IJF��F�-X�́�g��՚���>���-f�:#����?}�ϡ6�S���9�۟��k�8o��'��`Mɨ(��`��p���!�UU؂s��a0(�(j8�/ZX�������a��؉�`&�i��7�����k��B.��5��%�/�mV.�a/mq4ݍ^�f������/B$���9�E(��<܃}O��ǯ}I>����&Q���@_d˔@�w��#v��3�7W�_1�m�ľ��Iab`P'�h���|����r�Lbs�»�zc�.6�~��M\��r��ս��RLC@?|	U��*F���4�?���mi$����8�q
g|���J�L���yTJ��X�����ȕĳQsF�V��~��WJ`!�����%t_`S&����׎4�=H�i'&���ط憳V
��vL�d�B��tō������m�����Mhhax�L1A���/F$���aq��mJ����!�M i���	.]��\����y$�%1�O�� 퇕�EfY�J�`a���RL��"�x�Q33ǉ&��7T*��0�y�3���!���Yz������E�tm�|� �/䏋�i�[Q	<x��������� īj��9|���Zg�x#�tx��|3�=�)�����!ə�ah�Lߏ�2����FȤP.���t \:nR߄U���k����H��	]��~`F?�ƘL �S��=
�t6�y�J����8ɓ"g�T���A'�T����Y����^��1V$��>�b����5&�M� �����<>f���j,�nhj�`w�4RF�u[����M�Z�#�$̅�N��5M�ne�I������Qj��lGg�D�@�1��T ��zU=V������9F)�̥�b�H���[β'��ǢgA��a5y��)a������40�ǰەB�K�pI�����p�Xn�ON
J�&:{�D�
������
&EA��K�Q	���F���1{�6�k%�����©�X8�s�8�'c�M��I�hDJ&>:I︤��#�ҳO��Y���ꡩ{�@��h�u�����}��    ����������zO"B�<�{�$s��dd
�7Ve�c0[=S�ʛ���]/9���u��Ş1�3f6W�Ff�jo��q3��=��%jO31�	�Kk�\5�F&�,�
������O7��s��02��Ͳf|��S���
� �B���g=l���?k�~D��6]+���Y���pA���RG��Y
�ǲ�N�I@׳l�,�1���&+���,u@�@��,>�ʧ�4�-�{D~6��=d���P���X���1�=����S9����V)�ʇ�)z��������V�Q_U���O`/��ߚ=��0��^��p_��;F��j��;2V����~e΋0}ߕ)eG��3��\��jP|R�5�x[���f��Y�{h^NlE!B2s�S�D��Z;:�g��rt`�>��ZI0�m��;3<�f<|�u�yV�f'-�r:�0��U����%"�!/:���̄uߵr;{%<���Cs���p��0��bA ���뾾�E�F�v�bMBCw��-	^[hv80�>84=��Hr�������P��67/$�N,|��i�A\b4k��Id�}��(�ۭ�R�T#��U�Iok��9�����Ғ���:[�C�{��s/�'�Z�z%Y, W��:�K.� |N�j���������L������eq��:��T�*&v�U{_�F|&�&c,�8���˘#�h���`K*�[�3�d�5��U�7>u�w���p��G<��&����17�į�)4�������8-���i�+�Ď�5���DZ���fS����O	���SΦ�����c�u(�W�o���c���we4X4ƴo�U��V���s�c��`hO Q
@�%h�/0{0on�]u��*Kh77;ّ2ƨWf�9W�7��¬H���h�ٽ� ��N[1��!�Po#�.D���Ľ�XuC{�j'j��/�SUbB
`B||�H@�w�節�̧�]��ݮj�0� �QI��{��iq�S����;��˪Փ)�q�h��r�~�>����g@�=7�y�̲b�"��k4V>��_V�a��%3W�R��U3�]Et�*�d�P���',�V0.΀xe!U`�6�yAX�؏�T�I�R�	V���+T�{��i=sY��/�P�oߨ:� ���'Vw���H��˵��c��{	,i�IJ�G��r�w�A�$w��h�c���,��|�$x��B�jsh*BX���M�Q�r���f@
�q�+,���҂>�% ��}�)����^��6#�xf�y���;<������Ⱦ*�'ֹX��vaS��gv(�i�(lB ���]�+,6��(���x�::Aj�n3��W ��u�ÒRBH8�� &T-�o�p6뮕EVbϨ� Ǻ3&!�[<\b_0����;��O�_�K����a����9�/�rHy4:
]��|�p�MK�2��:������+ �Vzo�`�R�c�,�I�&�/��y��򀃖h��x������R�`X~�-�Z,µ<���:��o*��U��,.�Ϫ�:*?#Y����dY�`�ŀT�_�6�C��*�eE���~(�o��{�n�K|,�ró��%���'O��`l ��\�U�0�Ĝ1�P�������#��n}`�Zaρg@RE��^������`�Ƭ����5��� �.��t(��e��p~��� �6�vǁ�\�S�[�@:>p�e�5�z$��y�R���Ԣ����I��\�����<PB�!E���-\��3��X#$VZ&�5�������j�sQ�$��XG��Dߛ�mc���tj�x���Ǆ�3Ӫ2�C�FV��s��a���.������j&���^s���ՂM��������F�3$��X1�	�9La�m~X$y���ҷ�0Fr�:���V���jF�}p�����:�Y���6?1/W�ɝ��B-k����al�?v�i��	?� ��$̾AU�Q�YI��z�ò;(�(Ŵ����Wq{�ٰװ"X�N������"h�3c�L�_��R?+���]�vj?[�&�n���x.!�3����b�7�L�a��u`���P��\�����5<�@ߢ�9}��$�e�8����/V�i�i��Q���۶ˍ���Y���h,#��h�.;�UwRX��[��!U[�],\��v|�0Q���s����r��EP�pW��:�d������0��'+k^�Y�|r ��N"nҶ�I"��ΐ�!P� �.qPh��nu�"W����x"*� `�`��v���������R2��㥙��|���,VV1ʱ`p�m�z��r�a��,_,&��Q	�����hR_0��(�d7�	 n��A#W���}�kj.���A!�K�XK���������U��5/��ڮ���W��a���Nz����n5���7:=�'
tHǔ5�aK٧��<���enX����V6���C�f�\VDGCs1��U�-�cо�DiGL�����܅��24;�6���s�E����m�Z����X\���i�KCʓ�qS�WƋc��ٶ}��4���;�T��Tt�ù'�%M�q�o{,N08��U���e��%��y��=x����D;g����'A�"0��,]In';O���5�M����x$���u(�5��Fo�K�'���_�gm紈�p���Ћc��]�|�d�}n����y�K� #��y��������&��������4�7�ps���0;h��of��yz�7,9�κ\l��Z���o|�yV@>�u�N9�#���r�e �:9|��w�Z���99��pR�	���jC�,� =/5���%LFHN�y���,<�5[qC�v�Po�Z^)��À���"#c�B3j�WE�\�2x拓�K�����N�%�����z�Ǻ�i�[��W�f�i�mu������ܾ��4*b�O�X3?�.����y1aG�'N��Ӽ�����B٠�XH/���X)�l��h�`�1>���le��U��,τ���W#��@��ĲR��DlZ�/���c�����?�8�]�������z��J��s�z�pd7>ɸ�7s� �2�1�D>�x�C����X�8�`���l4���X�:I�X�En���R�O��f�]꘬�A�]y���ϵw��`��E���4�u�6��U�OS���N��v�s���k��)I4=+Ƽ:��8��I��a���A�B���W�D.@��Ѭ�jp��h�� �rw<�r����T���Qpx	e5/1��"�~��"�9�!��Eǈ��Mhib��I@GL:�b����Abƫe���d��]E���b����m%��4K��kc�$�O��
#r�o����r5�Z��7m�$N�/y 7W���Xؚ�����Y����/�&�7Ί&�U�@]��u6ڕ��+��?<�a�"���kx�,N�ذ:N#�q��N\��ڛe_��Ѱ����d�?�¤d8N��F~�*|�^i���B(m}�&��2���[���<���̓������X�$�
�=D)�i:&άOb!�L(k����N��0I��{�\Y݄�c�+�0�qx�Zԓ��<K�9���v�y�#�T�;l����q�y�
���Q� ,y����4)��0���i�+���ϩ�)���������7��}h����GP�NѸ)��.c�3�I�2�
V�G�]�C�`�,�el��1>�WN��q�E�����<���J	��� ��a�u��.<���c���n��a���$�<?����1�:tL5�n��^v�&��+̃�1�I�'ΰ����=�U/rF
k��������){;�e�3��3�1�~�1c=y�g'�|�v�d�	q�%z���62fD��������ΐ���O՚~~Q�.���`���.��.�3�H���r��iG巫��Ɓ�qis^ڰ��^5$����9���
��b90g@2� �jUL �  *��8(���EU'�i��D\�V�#-*����B���!����j�8��2l���ou$dJ\9?�/m�/��n�H����2��, �, �����N�-�o)����Z0��/x��O?�Hh�Z^N�'hG~�y��`�*5+� ��T�TL���AL��&��<E{z�ڐ���~f!U{�d���v�y�J��3Ь�%���c���N�s�?^R�7���8/ ۥ+}ww�s����n��L��*b���}G��X7V9+�?5�j��4$�ʍ��ۆְ�XV���ű�G�)@ӓ�5�N�]Yh��^�u�����ޢR�sV���L�FYK������dŜ|s�u��Ȕѓk��F�I������8�L�ٱ���kB������Ĥ8������QfWSI�Y�
f� m���� g2�!����N6x��o+j[��h<�k��N
7���oN����9���D1�T�/jՃ+GS�M�ꆇ� �g���2Cé�qI���G%4��7֜�zg=S�?����%޲Q�h\`��O�X���9:��k��ʖ�Ds⛊66��ƭ���s�t<ޏ���a\`+)����ıI����	kf�����j��9����U�O2P�)[�CW�`�JJ��Z9�X<�7�xs���v���ji&(���8����b����c�&kXx��_,��M1x��.C�;���G�j$p߾ᗏE�q	QQN�+%�<_�B2����/��TX���:��x�ey~'��\�����ni-��.%!Gs<{�� ���w�f�������l��o��!�]�D; �sZ0�@�l���t� ��hl�VB��D�rx��I�K��r5_�iMP�RՏ<ᝄ�2�<7i ��;;M�����b�EH�g=F��g+2��x��"<��ƃ���L!���x���(-;=~��q%�k̲��$�ҁ_B7���J��+���ã��1��0�[^�4J@G�����s���G��o�&q��^����M��*�����4���d&&�n�^Z+�o�s-Y�j��%�i/N�o�n�No�zN}v��c����γ�/v B�5�<�:��c/�ͭ��R����#��Ķ�?�xnS�Z�sm��j�E<9B�dq��S��g;b8^���z�v�_�_7�x�c�����t�Q�	P39II$��i��g�3gg��������z�m�y�<F&� ����@�z����#�p}ښ75�P��>����`U�;,��q�����q��tPB�i�����
��Ç�iD��QF�Oey���F���V+١!u��u�ò�M�Jx�ڱ�o���[�a?�e�ド΁�c�X���w��@���YT��2=��j5\?��u=��R+�a���k���v^Ī�Ґ�E��Lp<ٓ?����G��M�zAsB"���p�g�%s4;K#q�_nNO�l	^��s�|���~QݶL�:���@-oKdwKq���I�������Y�W��y!c�hzߊæ�n�(�`缴оks�GH�b�I'�E̧Ƞ"x�i�c������RA�X ��V��
j�jG<<+Mx��Eu���a�V��G�F�-��V�w�����*żlN����S��EiyBH�+x�>�%SuA:/�tH�m��r=����7KR��+o��Ř�wa8B��G���x�����xG�u��;�%e�7Vc��Kx��Σ^��R<��:JiK��.����>L�rۥ�#�3>W��W#�[�Zwug�{y&�xn�(/S��#����ð�U$r��?Ib%�b,T���V|h����t������x� ��i����T�Ё�����5��hv�CΏ|98��,��J�oG�Խ.�8xg��w����p���q4����D�C�I���|�h�pp�����C]���o�����"�b�[�������l�� �      V      x������ � �      0      x������ � �      2      x������ � �      4   !  x����J�0��ӧ���I��n8��0�ڰ�d$u�=��(�0����9|��V�
����I��t�f�!����z"��+!k�a4��)����q�#<��vf~��Ԝ���Z�<`�RG
�[KC��N��%GY=k�5�����YåTm�ZX�@V��)��><S������L�cJK��9�Dӡ(Wp�ǤN�:e���]��R�6����R!<ґ�9P�E0;r��7ݛ���� �tR�X]���+ ln?��D��m�M����Y�j��{�e���G      6      x������ � �      P      x������ � �      R      x������ � �      8      x������ � �      :   3   x�3�4202�54�50U00�2��25�31�414�tt���������� ��,      <      x������ � �      >      x������ � �      @   6  x��KN�0����)r�e�>x4�Rh�f�Z���#'�Do�%V!c<��X�G�rt��\M-�����;]��q�C���"�2OU ��5�y��9�����<^Ǟ@N�6n��&��n����ۘ+j�Q2x��,N�aL���jT*�LB���B|�)ߊJpS(�o���+�0���"�A���X��������چ|ǧ���w�
��X_`J~������1�>쌵Sa��*��#�|�;����P����W�f04m�M璜ӑ�T�k�J�뭮KCm2�}y����SD�[�~�      T      x������ � �      L      x������ � �      B   Z   x�3�4�4bN�DCC���Ҩ��2�<c=,�Т̼���`�� � ����p�ܔⲂ����в�p�Ĕ��<NC�=... ��      D      x�3�4�4����� �X      X      x������ � �     