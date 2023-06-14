
--liquibase formatted sql
--changeset AUTHOR:antcast
--comment OPTIONAL COMMENT


CREATE TABLE IF NOT EXISTS product (
    id integer NOT NULL,
    name character varying(255),
    description character varying(255),
    product_status character varying(50)
);
ALTER TABLE product DROP CONSTRAINT IF EXISTS product_pkey;
ALTER TABLE product ADD CONSTRAINT product_pkey PRIMARY KEY (id);

CREATE TABLE IF NOT EXISTS dashboard (
    user_id integer NOT NULL,
    widget_name character varying(100) NOT NULL,
    widget_index integer NOT NULL,
    x character varying(50) NOT NULL,
    y character varying(50) NOT NULL,
    width character varying(50) NOT NULL,
    height character varying(50) NOT NULL,
    description character varying(255),
    created_on timestamp without time zone DEFAULT now() NOT NULL,
    updated_on timestamp without time zone DEFAULT now() NOT NULL
);
ALTER TABLE dashboard DROP CONSTRAINT IF EXISTS dashboard_pk;
ALTER TABLE dashboard ADD CONSTRAINT dashboard_pk UNIQUE (user_id, widget_name);


CREATE TABLE IF NOT EXISTS notification (
    notification_id integer NOT NULL,
    user_id integer NOT NULL,
    details character varying(255),
    display boolean DEFAULT false NOT NULL,
    created_on timestamp without time zone DEFAULT now() NOT NULL,
    updated_on timestamp without time zone DEFAULT now() NOT NULL
);
ALTER TABLE notification DROP CONSTRAINT IF EXISTS notification_pk;
ALTER TABLE notification ADD CONSTRAINT notification_pk UNIQUE (notification_id);


CREATE TABLE IF NOT EXISTS quick_links (
    link_id integer NOT NULL,
    link_name character varying(100) NOT NULL,
    created_on timestamp without time zone DEFAULT now() NOT NULL,
    updated_on timestamp without time zone DEFAULT now() NOT NULL
);
--ALTER TABLE quick_links DROP CONSTRAINT IF EXISTS quick_links_pk;
--ALTER TABLE quick_links ADD CONSTRAINT quick_links_pk UNIQUE (user_id, link_name, link_position);


CREATE TABLE IF NOT EXISTS widget (
    widgetId integer NOT NULL,
    widgetName character varying(100) NOT NULL,
    template character varying(50) NOT NULL,
    imageUrl character varying(50) NOT NULL,
    active boolean DEFAULT true NOT NULL,
   
  
    createdOn timestamp without time zone DEFAULT now() NOT NULL,
   
    updatedOn timestamp without time zone DEFAULT now() NOT NULL,
    numberOfRecords integer NOT NULL
);


CREATE TABLE IF NOT EXISTS dashboard_preference (
    userPreferenceId integer NOT NULL,
    widgetQuickLink json NOT NULL,   
    createdBy timestamp without time zone DEFAULT now() NOT NULL,
    createdOn timestamp without time zone DEFAULT now() NOT NULL,
    updatedBy timestamp without time zone DEFAULT now() NOT NULL,
    updatedOn timestamp without time zone DEFAULT now() NOT NULL
);



