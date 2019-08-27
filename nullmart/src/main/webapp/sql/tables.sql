
/* Drop Tables */

DROP TABLE ORDER_TABLE CASCADE CONSTRAINTS;
DROP TABLE CART CASCADE CONSTRAINTS;
DROP TABLE DELIVINFO CASCADE CONSTRAINTS;
DROP TABLE ACCOUNT CASCADE CONSTRAINTS;
DROP TABLE BOARD CASCADE CONSTRAINTS;
DROP TABLE EVAL CASCADE CONSTRAINTS;
DROP TABLE STOCK CASCADE CONSTRAINTS;
DROP TABLE PRODUCT CASCADE CONSTRAINTS;
DROP TABLE SEARCHEDWORD CASCADE CONSTRAINTS;
DROP TABLE MANAGER CASCADE CONSTRAINTS;




/* Create Tables */
create table MANAGER
(
  masteruserid varchar2(10) primary key,
  masterpasswd varchar2(10) not null,
  masterusername varchar2(10) not null,

  masterphone1 varchar2(3) not null,
  masterphone2 varchar2(4) not null,
  masterphone3 varchar2(4) not null,
  masteremail1 varchar2(20) not null,
  masteremail2 varchar2(20) not null
);

CREATE TABLE ACCOUNT
(
	-- ID Value
	USERID varchar2(25) NOT NULL,
	-- 비밀번호
	PASSWORD varchar2(25) NOT NULL,
	-- user name
	NAME varchar2(80) NOT NULL,
	-- user sex
	-- 
	SEX varchar2(2) NOT NULL,
	-- user email1
	EMAIL1 varchar2(80) NOT NULL,
	-- user email2
	EMAIL2 varchar2(80) NOT NULL,
	-- 수령자 우편번호
	-- 
	POST varchar2(80) NOT NULL,
	ADDRESS1 varchar2(80),
	-- user 상세주소
	-- 
	ADDRESS2 varchar2(80) NOT NULL,
	-- 010 011
	PHONE1 varchar2(5) NOT NULL,
	PHONE2 varchar2(5) NOT NULL,
	PHONE3 varchar2(5) NOT NULL,
	PRIMARY KEY (USERID)
);


CREATE TABLE BOARD
(
	-- 게시글 번호
	BNO varchar2(10),
	-- 상품 고유 번호
	-- 
	PCODE varchar2(10 char) NOT NULL,
	-- 게시글에 들어갈 text/html
	CONTENT varchar2(4000)
);


CREATE TABLE CART
(
	-- 카드 고유 번호
	CNO varchar2(10 char) NOT NULL,
	-- ID Value
	USERID varchar2(25) NOT NULL,
	-- 상품 고유 번호
	-- 
	PCODE varchar2(10 char) NOT NULL,
	-- 상품 이름
	PNAME varchar2(10 char) NOT NULL,
	--  상품 크기,
	-- 200 부터 300까지 10단위로 구성한다.
	-- 예) 240,250,260..
	PSIZE varchar2(5 char) NOT NULL,
	--  색상,
	-- 상품 마다 다르며 색상은 영문 문자열로 구성한다.
	--   예) RED, BLUE, PINK...
	PCOLOR varchar2(5 char) NOT NULL,
	-- 상품 가격 
	PPRICE number(20) NOT NULL,
	-- 상품 수량
	-- 
	PAMOUNT number(5,0),
	CONSTRAINT CART_CNO_PK PRIMARY KEY (CNO)
);


CREATE TABLE DELIVINFO
(
	-- 회원배송지번호
	-- sequence 사용 
	-- 1 증가 
	DELIVNO number(10,0) NOT NULL,
	-- 배송지명
	DELIVNAME varchar2(10) NOT NULL,
	DELIVPERSON varchar2(10) NOT NULL,
	-- 전화번호 1
	PHONE1 varchar2(3) NOT NULL,
	-- phone2
	PHONE2 varchar2(4) NOT NULL,
	PHONE3 varchar2(4) NOT NULL,
	-- 주소1
	ADDRESS1 varchar2(80) NOT NULL,
	-- 주소2
	ADDRESS2 varchar2(80) NOT NULL,
	-- ID Value
	USERID varchar2(25) NOT NULL,
	PRIMARY KEY (DELIVNO)
);


CREATE TABLE EVAL
(
	-- 평가번호
	-- 데이터 입력시 ONO+"EVAL"로 자동 입력
	EVALNO varchar2(5) NOT NULL,
	-- 주문평가점수
	ORDERSCORE number(2,0) NOT NULL,
	-- 배송점수
	FASTDELIVERY number(2,0),
	-- 상품만족도
	ORDERSATIS number(2,0),
	EVALCONTENT varchar2(1000),
	PRIMARY KEY (EVALNO)
);


CREATE TABLE ORDER_TABLE
(
	-- 주문 고유 번호
	ONO varchar2(10) NOT NULL UNIQUE,
	-- 카드 고유번호
	CNO varchar2(10 char) NOT NULL,
	-- ID Value
	USERID varchar2(25) NOT NULL,
	-- 수령인 이름
	ORDERNAME varchar2(20) NOT NULL,
	-- 수령자 우편번호
	-- 
	POST varchar2(80) NOT NULL,
	-- 수령인 지번
	ADDRESS1 varchar2(80) NOT NULL,
	-- 수령인 상세주소
	-- 
	ADDRESS2 varchar2(80) NOT NULL,
	-- 특수문자 없는 수령인 폰번호 
	--  예) 01044567789
	-- 
	PHONE varchar2(20) DEFAULT '' NOT NULL,
	-- 결제수단
	-- 
	PAYMETHOD varchar2(10) NOT NULL,
	-- 주문 날짜
	ORDER_DATE date NOT NULL,
	-- 평가번호
	-- 데이터 입력시 ONO+"EVAL"로 자동 입력
	EVALNO varchar2(5) NOT NULL,
	PRIMARY KEY (ONO)
);


CREATE TABLE PRODUCT
(
	-- 상품 고유 번호
	-- 
	PCODE varchar2(10 char) NOT NULL,
	-- 여성화, 남성화
	-- 
	STYLETOP varchar2(10 char) NOT NULL,
	-- 운동화, 스포츠화, 구두, 부츠
	-- 
	STYLEMID varchar2(10 char) NOT NULL,
	-- stylemid의 상세 분류
	-- 운동화 ) 캔버스화, 스니커즈
	-- 스포츠화 ) 런닝화, 등산화
	-- 구두 ) 힐, 옥스퍼드
	-- 부츠 ) 워크화, 털부츠
	STYLEBOT varchar2(10 char) NOT NULL,
	-- 상품 이름
	PNAME varchar2(10 char) NOT NULL,
	--  이미지 파일명(확장자 포함)
	PIMAGE varchar2(10 char) NOT NULL,
	-- 상품 등록일
	-- 
	PREGITDATE date DEFAULT systimestamp  NOT NULL,
	CONSTRAINT PRODUCT_PCODE_PK PRIMARY KEY (PCODE)
);


CREATE TABLE STOCK
(
	-- 재고번호
	-- pname,size,color ->재고코드
	-- RED/250/VRT-09
	SCODE varchar2(80) NOT NULL,
	-- 상품 고유 번호
	-- 
	PCODE varchar2(10 char),
	--  상품 크기,
	-- 200 부터 300까지 10단위로 구성한다.
	-- 예) 240,250,260..
	PSIZE varchar2(5 char),
	--  색상,
	-- 상품 마다 다르며 색상은 영문 문자열로 구성한다.
	--   예) RED, BLUE, PINK...
	PCOLOR varchar2(5 char),
	-- 상품 가격 
	PPRICE number(20) NOT NULL,
	-- 상품 수량
	-- 
	PAMOUNT number(5,0),
	-- 배송비 유무
	-- Y = 3000원
	-- N = 무료 배송
	DELIVERFEE_YN varchar2(3 char) DEFAULT '''Y'' ',
	PRIMARY KEY (SCODE)
);


CREATE TABLE SEARCHEDWORD
(
	-- 검색된 단어별 코드
	SNO varchar2(10 char) NOT NULL,
	-- 검색된 코드
	SWORD varchar2(20 char) NOT NULL,
	-- 특정 단어가 검색된 횟수
	SEARCHEDCNT number(10,0) DEFAULT 1 ,
	CONSTRAINT SEARCHEDWORD_NO_PK PRIMARY KEY (SNO)
);



/* Create Foreign Keys */

ALTER TABLE CART
	ADD FOREIGN KEY (USERID)
	REFERENCES ACCOUNT (USERID)
;


ALTER TABLE DELIVINFO
	ADD FOREIGN KEY (USERID)
	REFERENCES ACCOUNT (USERID)
;


ALTER TABLE ORDER_TABLE
	ADD FOREIGN KEY (USERID)
	REFERENCES ACCOUNT (USERID)
;


ALTER TABLE ORDER_TABLE
	ADD FOREIGN KEY (CNO)
	REFERENCES CART (CNO)
;


ALTER TABLE ORDER_TABLE
	ADD FOREIGN KEY (EVALNO)
	REFERENCES EVAL (EVALNO)
;


ALTER TABLE BOARD
	ADD FOREIGN KEY (PCODE)
	REFERENCES PRODUCT (PCODE)
;


ALTER TABLE STOCK
	ADD CONSTRAINT STOCK_PCODE_FK FOREIGN KEY (PCODE)
	REFERENCES PRODUCT (PCODE)
;



/* Comments */

COMMENT ON COLUMN ACCOUNT.USERID IS 'ID Value';
COMMENT ON COLUMN ACCOUNT.PASSWORD IS '비밀번호';
COMMENT ON COLUMN ACCOUNT.NAME IS 'user name';
COMMENT ON COLUMN ACCOUNT.SEX IS 'user sex
';
COMMENT ON COLUMN ACCOUNT.EMAIL1 IS 'user email1';
COMMENT ON COLUMN ACCOUNT.EMAIL2 IS 'user email2';
COMMENT ON COLUMN ACCOUNT.POST IS '수령자 우편번호
';
COMMENT ON COLUMN ACCOUNT.ADDRESS2 IS 'user 상세주소
';
COMMENT ON COLUMN ACCOUNT.PHONE1 IS '010 011';
COMMENT ON COLUMN BOARD.BNO IS '게시글 번호';
COMMENT ON COLUMN BOARD.PCODE IS '상품 고유 번호
';
COMMENT ON COLUMN BOARD.CONTENT IS '게시글에 들어갈 text/html';
COMMENT ON COLUMN CART.CNO IS '카드 고유 번호';
COMMENT ON COLUMN CART.USERID IS 'ID Value';
COMMENT ON COLUMN CART.PCODE IS '상품 고유 번호
';
COMMENT ON COLUMN CART.PNAME IS '상품 이름';
COMMENT ON COLUMN CART.PSIZE IS ' 상품 크기,
200 부터 300까지 10단위로 구성한다.
예) 240,250,260..';
COMMENT ON COLUMN CART.PCOLOR IS ' 색상,
상품 마다 다르며 색상은 영문 문자열로 구성한다.
  예) RED, BLUE, PINK...';
COMMENT ON COLUMN CART.PPRICE IS '상품 가격 ';
COMMENT ON COLUMN CART.PAMOUNT IS '상품 수량
';
COMMENT ON COLUMN DELIVINFO.DELIVNO IS '회원배송지번호
sequence 사용 
1 증가 ';
COMMENT ON COLUMN DELIVINFO.DELIVNAME IS '배송지명';
COMMENT ON COLUMN DELIVINFO.PHONE1 IS '전화번호 1';
COMMENT ON COLUMN DELIVINFO.PHONE2 IS 'phone2';
COMMENT ON COLUMN DELIVINFO.ADDRESS1 IS '주소1';
COMMENT ON COLUMN DELIVINFO.ADDRESS2 IS '주소2';
COMMENT ON COLUMN DELIVINFO.USERID IS 'ID Value';
COMMENT ON COLUMN EVAL.EVALNO IS '평가번호
데이터 입력시 ONO+"EVAL"로 자동 입력';
COMMENT ON COLUMN EVAL.ORDERSCORE IS '주문평가점수';
COMMENT ON COLUMN EVAL.FASTDELIVERY IS '배송점수';
COMMENT ON COLUMN EVAL.ORDERSATIS IS '상품만족도';
COMMENT ON COLUMN ORDER_TABLE.ONO IS '주문 고유 번호';
COMMENT ON COLUMN ORDER_TABLE.CNO IS '카드 고유번호';
COMMENT ON COLUMN ORDER_TABLE.USERID IS 'ID Value';
COMMENT ON COLUMN ORDER_TABLE.ORDERNAME IS '수령인 이름';
COMMENT ON COLUMN ORDER_TABLE.POST IS '수령자 우편번호
';
COMMENT ON COLUMN ORDER_TABLE.ADDRESS1 IS '수령인 지번';
COMMENT ON COLUMN ORDER_TABLE.ADDRESS2 IS '수령인 상세주소
';
COMMENT ON COLUMN ORDER_TABLE.PHONE IS '특수문자 없는 수령인 폰번호 
 예) 01044567789
';
COMMENT ON COLUMN ORDER_TABLE.PAYMETHOD IS '결제수단
';
COMMENT ON COLUMN ORDER_TABLE.ORDER_DATE IS '주문 날짜';
COMMENT ON COLUMN ORDER_TABLE.EVALNO IS '평가번호
데이터 입력시 ONO+"EVAL"로 자동 입력';
COMMENT ON COLUMN PRODUCT.PCODE IS '상품 고유 번호
';
COMMENT ON COLUMN PRODUCT.STYLETOP IS '여성화, 남성화
';
COMMENT ON COLUMN PRODUCT.STYLEMID IS '운동화, 스포츠화, 구두, 부츠
';
COMMENT ON COLUMN PRODUCT.STYLEBOT IS 'stylemid의 상세 분류
운동화 ) 캔버스화, 스니커즈
스포츠화 ) 런닝화, 등산화
구두 ) 힐, 옥스퍼드
부츠 ) 워크화, 털부츠';
COMMENT ON COLUMN PRODUCT.PNAME IS '상품 이름';
COMMENT ON COLUMN PRODUCT.PIMAGE IS ' 이미지 파일명(확장자 포함)';
COMMENT ON COLUMN PRODUCT.PREGITDATE IS '상품 등록일
';
COMMENT ON COLUMN STOCK.SCODE IS '재고번호
pname,size,color ->재고코드
RED/250/VRT-09';
COMMENT ON COLUMN STOCK.PCODE IS '상품 고유 번호
';
COMMENT ON COLUMN STOCK.PSIZE IS ' 상품 크기,
200 부터 300까지 10단위로 구성한다.
예) 240,250,260..';
COMMENT ON COLUMN STOCK.PCOLOR IS ' 색상,
상품 마다 다르며 색상은 영문 문자열로 구성한다.
  예) RED, BLUE, PINK...';
COMMENT ON COLUMN STOCK.PPRICE IS '상품 가격 ';
COMMENT ON COLUMN STOCK.PAMOUNT IS '상품 수량
';
COMMENT ON COLUMN STOCK.DELIVERFEE_YN IS '배송비 유무
Y = 3000원
N = 무료 배송';
COMMENT ON COLUMN SEARCHEDWORD.SNO IS '검색된 단어별 코드';
COMMENT ON COLUMN SEARCHEDWORD.SWORD IS '검색된 코드';
COMMENT ON COLUMN SEARCHEDWORD.SEARCHEDCNT IS '특정 단어가 검색된 횟수';



