--
-- User entity
--
DROP TABLE IF EXISTS USER;
CREATE TABLE IF NOT EXISTS USER
(
    ID               BIGINT PRIMARY KEY,
    USERNAME         VARCHAR(31)         NOT NULL,
    PASSWORD         VARCHAR(255)        NOT NULL,
    PASSWORD_EXPIRED DATE,
    EMAIL            VARCHAR(127),
    NAME             VARCHAR(63)         NOT NULL,
    FAMILY_NAME      VARCHAR(63),
    NICKNAME         VARCHAR(63),
    LOCKED           CHAR(1) DEFAULT 'N' NOT NULL,
    DELETED          CHAR(1) DEFAULT 'N' NOT NULL,
    CREATED          TIMESTAMP,
    CREATOR          VARCHAR(31),
    MODIFIED         TIMESTAMP,
    MODIFIER         VARCHAR(31)
);
INSERT INTO USER (ID, USERNAME, PASSWORD, EMAIL, NAME, PASSWORD_EXPIRED)
VALUES (-1, 'tester', '{noop}test123$', 'tester@test.com', 'foo', ADD_MONTHS(CURRENT_DATE, 3));
INSERT INTO USER (ID, USERNAME, PASSWORD, EMAIL, NAME, PASSWORD_EXPIRED)
VALUES (-2, 'leader', '{noop}lead123$', 'leader@test.com', 'bar', ADD_MONTHS(CURRENT_DATE, 3));


DROP TABLE IF EXISTS AUTHORITY;
CREATE TABLE IF NOT EXISTS AUTHORITY
(
    ID       BIGINT PRIMARY KEY,
    ROLE     VARCHAR(31)         NOT NULL,
    UPPER_ID INT,
    DELETED  CHAR(1) DEFAULT 'N' NOT NULL,
    CREATED  TIMESTAMP,
    CREATOR  VARCHAR(31),
    MODIFIED TIMESTAMP,
    MODIFIER VARCHAR(31)
);

INSERT INTO AUTHORITY (ID, ROLE, UPPER_ID)
VALUES (-1, 'ADMIN', NULL);
INSERT INTO AUTHORITY (ID, ROLE, UPPER_ID)
VALUES (-2, 'MANAGER', -1);
INSERT INTO AUTHORITY (ID, ROLE, UPPER_ID)
VALUES (-3, 'INTERN', -2);
INSERT INTO AUTHORITY (ID, ROLE, UPPER_ID)
VALUES (-4, 'POST_WRITE', -3);
INSERT INTO AUTHORITY (ID, ROLE, UPPER_ID)
VALUES (-5, 'POST_READ', -3);
INSERT INTO AUTHORITY (ID, ROLE, UPPER_ID)
VALUES (-6, 'POST_REMOVE', -4);

DROP TABLE IF EXISTS USER_AUTHORITY;
CREATE TABLE IF NOT EXISTS USER_AUTHORITY
(
    ID           BIGINT PRIMARY KEY,
    USER_ID      BIGINT              NOT NULL,
    AUTHORITY_ID BIGINT              NOT NULL,
    DELETED      CHAR(1) DEFAULT 'N' NOT NULL,
    CREATED      TIMESTAMP,
    CREATOR      VARCHAR(31),
    MODIFIED     TIMESTAMP,
    MODIFIER     VARCHAR(31)
);
INSERT INTO USER_AUTHORITY (ID, USER_ID, AUTHORITY_ID)
VALUES (-1, -1, -2);
INSERT INTO USER_AUTHORITY (ID, USER_ID, AUTHORITY_ID)
VALUES (-2, -1, -6);
INSERT INTO USER_AUTHORITY (ID, USER_ID, AUTHORITY_ID)
VALUES (-3, -2, -4);


--
-- Menu entity
--
DROP TABLE IF EXISTS MENU;
CREATE TABLE IF NOT EXISTS MENU
(
    ID       BIGINT PRIMARY KEY,
    NAME     VARCHAR(255)        NOT NULL,
    NAME_EN  VARCHAR(255)        NOT NULL,
    URI      VARCHAR(255),
    ORD      NUMBER  DEFAULT 0,
    DSC      VARCHAR(1023),
    BADGE    VARCHAR(15),
    ICON     VARCHAR(63),
    CLASSES  VARCHAR(127),
    INACTIVE CHAR(1) DEFAULT 'N',
    USE      CHAR(1) DEFAULT 'N',
    UPPER_ID BIGINT,
    DELETED  CHAR(1) DEFAULT 'N' NOT NULL,
    CREATED  TIMESTAMP,
    CREATOR  VARCHAR(31),
    MODIFIED TIMESTAMP,
    MODIFIER VARCHAR(31)
);
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
VALUES (0, '관리자메뉴', '', '', 0, '', NULL);

INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
VALUES (-1, '주요업무', 'Main Works', '', 0, 'Remember an earning yours meal!', 0);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-2, '신청접수', 'Receipt', '/work/receipt', 0, '', -1);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-3, '처리결과', 'Approve Process', '/work/approve-process', 1, '', -1);

INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
VALUES (-4, '시스템', 'System', '', 1, 'Operating environment of system', 0);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-5, '사용자관리', 'User', '/system/user', 0, 'approve new user, denied, etc.', -4);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-6, '메뉴관리', 'Menu', '/system/menu', 1, '', -4);

INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
VALUES (-7, '총괄표', 'Dashboard', '', 2, '', 0);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-8, '통계지표', 'Analytics', '/dashboard/analytics', 0, '', -7);
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
VALUES (-9, '페이지샘플', 'Pages', '', 3, '', 0);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-10, '인증', 'Authentication', '', 0, '', -9);
/**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**/
VALUES (-17, '회원가입', 'Sign up', '', 0, '', -10);
/**//**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**//**/
VALUES (-18, '기본', 'Classic', '/pages/authentication/sign-up/classic', 0, '', -17);
/**//**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**//**/
VALUES (-19, '세련됨', 'Modern', '/pages/authentication/sign-up/modern', 1, '', -17);
/**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**/
VALUES (-20, '로그인', 'Login', '', 1, '', -10);
/**//**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**//**/
VALUES (-21, '기본', 'Sign up', '/pages/authentication/login/classic', 0, '', -20);
/**//**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**//**/
VALUES (-22, '세련됨', 'Sign up', '', 1, '', -20);
/**//**//**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**//**//**/
VALUES (-23, '전체화면', 'Sign up', '/pages/authentication/login/fullscreen', 0, '', -22);
/**//**//**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**//**//**/
VALUES (-24, '화면분할', 'Sign up', '/pages/authentication/login/split-screen', 1, '', -22);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-11, '도움말', 'Help center', '/pages/help-center', 1, '', -9);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-12, '인트로', 'Home', '/pages/home', 2, '', -9);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-13, '지원도구', 'Support', '/pages/support', 3, '', -9);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-14, '오류페이지', 'Errors', '', 0, '', -9);
/**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**/
VALUES (-15, '404 페이지오류', 'Page not found', '', 0, '', -14);
/**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**/
VALUES (-16, '500 시스템오류', 'Internal server error', '', 1, '', -14);
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
VALUES (-25, '사용자 접근', 'User Interface', '', 4, 'Building elements of UI/UX', 0);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-26, '활동영상처리', 'Animation', '/user-interface/animation', 0, '', -25);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-27, '입력형태', 'Form', '', 1, '', -25);
/**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**/
VALUES (-28, '항목들', 'Fields', '/user-interface/form/fields', 0, '', -27);
/**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**/
VALUES (-29, '구조들', 'Layouts', '/user-interface/form/layouts', 1, '', -27);
/**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
    /**//**/
VALUES (-30, '마법사들', 'Wizards', '/user-interface/form/wizards', 2, '', -27);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-31, '형상화', 'Icons', '/user-interface/icons', 2, '', -25);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-32, '낱장', 'Cards', '/user-interface/cards', 3, '', -25);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-33, '색깔', 'Colors', '/user-interface/colors', 4, '', -25);

INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
VALUES (-999, '문서화', 'Documentation', '', 999,
        'Usage guides for everything you need to know about it', NULL);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-990, '변천사', 'Changelog', '/documentation/changelog', 0, '', -999);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-991, '안내사항', 'Guides', '/documentation/guides', 1, '', -999);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-992, '핵심기능', 'Core Features', '/documentation/features', 2, '', -999);

INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
VALUES (-900, '항해 기능', 'Navigation Features', '', 900,
        'Collapsable levels and badge styles at menu item', NULL);
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID)
/**/
    VALUES (-901, '휘장', 'badges', '', 0, '', -900);
/**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID, BADGE)
    /**//**/
VALUES (-902, '동그란 휘장', 'Circle badge', '', 0, '', -901, 'Y');
/**//**/
INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID, BADGE)
    /**//**/
VALUES (-903, '네모난 휘장', 'Rectangle badge', '', 1, '', -901, 'Y');
/**//**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID, BADGE)
/**//**/VALUES (-904, '타원형 휘장', 'Round badge', '', 2, '', -901, 'Y');
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID, INACTIVE)
/**/VALUES (-905, '못쓰는 메뉴', 'Inactive Item', '', 3, '', -900, 'Y');
/**/INSERT INTO MENU (ID, NAME, NAME_EN, URI, ORD, DSC, UPPER_ID, INACTIVE)
/**/VALUES (-906, '못쓰는 메뉴', 'Inactive Item', '', 3, '', -900, 'Y');
