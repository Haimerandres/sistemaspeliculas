PGDMP     
                    x         	   peliculas    10.12    10.12     �
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �
           1262    16393 	   peliculas    DATABASE     �   CREATE DATABASE peliculas WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';
    DROP DATABASE peliculas;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �
           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �
           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16474    pelicula    TABLE     �   CREATE TABLE public.pelicula (
    id_pelicula integer NOT NULL,
    nombre_pelicula text,
    director text,
    tipo text,
    descripcion text,
    imagen text,
    rating integer,
    cantidadvotos integer
);
    DROP TABLE public.pelicula;
       public         postgres    false    3            �            1259    16472    pelicula_id_pelicula_seq    SEQUENCE     �   CREATE SEQUENCE public.pelicula_id_pelicula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.pelicula_id_pelicula_seq;
       public       postgres    false    197    3            �
           0    0    pelicula_id_pelicula_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.pelicula_id_pelicula_seq OWNED BY public.pelicula.id_pelicula;
            public       postgres    false    196            o
           2604    16477    pelicula id_pelicula    DEFAULT     |   ALTER TABLE ONLY public.pelicula ALTER COLUMN id_pelicula SET DEFAULT nextval('public.pelicula_id_pelicula_seq'::regclass);
 C   ALTER TABLE public.pelicula ALTER COLUMN id_pelicula DROP DEFAULT;
       public       postgres    false    196    197    197            �
          0    16474    pelicula 
   TABLE DATA               |   COPY public.pelicula (id_pelicula, nombre_pelicula, director, tipo, descripcion, imagen, rating, cantidadvotos) FROM stdin;
    public       postgres    false    197   O       �
           0    0    pelicula_id_pelicula_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.pelicula_id_pelicula_seq', 169, true);
            public       postgres    false    196            q
           2606    16482    pelicula pelicula_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.pelicula
    ADD CONSTRAINT pelicula_pkey PRIMARY KEY (id_pelicula);
 @   ALTER TABLE ONLY public.pelicula DROP CONSTRAINT pelicula_pkey;
       public         postgres    false    197            �
   �  x�ERK��0]+��c�����A
h��02c�#�.%Mn3�U�����Ȏ������� ^�8�$C��G��#�5����R��6�M�������ѝH#',���1N�}Uq'���C ���D��$%Q�ɐ�G[^3\*��j/�^b�H�_Ϣ�6�J��.qk�d������(E�1R*������*-w��+[M�w)��$/F�$}��x��Ŀh�·@�(���ĳ�`Z�[�`ve�L=�{��j2����׿�2���{l#zӢ��iG㻩ʤL�纾�-4K
y�l�����!���X��&#a�R̫�h�ַ-�3fl{|o:�md�ٞf�,	�m����g3Տ���6�X"�}�]��Y�Ѳ�-��7��k6u84��_ѽۼ0�u���S���     