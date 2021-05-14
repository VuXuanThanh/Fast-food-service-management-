CREATE TABLE TAIKHOANadmin
(
TenTaiKhoan VARCHAR(40) primary key NOT NULL,
MatKhau VARCHAR(16) not null
)

-- insert into taikhoanadmin
-- values
-- ('admin','123')


create table NHANVIEN
(
MaNhanVien INTEGER primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
TenNhanVien VARCHAR(40) NOT NULL,
GioiTinh VARCHAR(10) not null,
SoCMND VARCHAR(13),
NgaySinh date,
diachi VARCHAR(40),
tentaikhoan VARCHAR(40),
matkhau VARCHAR(16)
)
insert into nhanvien
(tennhanvien, gioitinh, socmnd, ngaysinh,diachi, tentaikhoan, matkhau)
values
('Mai','Nữ','12345679','2000-10-10','Hà Nội','MAI','mai123')
select * from nhanvien

create table Khachhang
(
makh INTEGER primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tenkh VARCHAR(40) NOT NULL,
soDT VARCHAR(10) not null,
diachi VARCHAR(40) 
)
-- insert into khachhang
-- (tenkh, sodt, diachi)
-- values
-- ('Trung Thế','029892','Hà Nội'),
-- ('Thành vũ','02323232','Hà Nội')
-- select * from khachhang

create table nguyenlieu
(
manl INTEGER primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tennl VARCHAR(40) NOT NULL,
donvitinh VARCHAR(10) not null,
hansudung INTEGER,
soluongtonkho INTEGER
)
-- insert into nguyenlieu
-- (tennl, donvitinh, hansudung, soluongtonkho)
-- values
-- ('trứng','quả',1,0),
-- ('thịt','kg',1,0)
-- select * from nguyenlieu
-- 
create table nhacungcap
(mancc INTEGER primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tenncc VARCHAR(40) NOT NULL, 
diachi VARCHAR(40) not null,
soDT VARCHAR(10)
)
-- insert into nhacungcap
-- (tenncc, diachi, sodt)
-- values
-- ('chợ Nhổn','Nhổn','028292'),
-- ('quán lẩu','Xuân Phương','028292')
-- select * from nhacungcap

create table nguyenlieu_nhacungcap
(
 mancc INTEGER,
manl INTEGER,
ngaycungcap date,
soluong INTEGER,
gianhap decimal,
CONSTRAINT nlncc_pk primary KEY (mancc,manl),
CONSTRAINT nlncc_fk FOREIGN KEY (manl) REFERENCES nguyenlieu(manl),
CONSTRAINT nlncc_fk2 FOREIGN KEY (mancc) REFERENCES nhacungcap(mancc)
)

-- insert into nguyenlieu_nhacungcap
-- values
-- (1,1,'2021-05-05',100,1000),
-- (1,2,'2021-05-05',200,1000),
-- select * from nguyenlieu_nhacungcap
create table danhmuc
(madm INTEGER primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tendm varchar(40)
)
-- insert into danhmuc
-- (tendm)
-- values
-- ('tráng miệng')

create table doan
(
mamon INTEGER primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tenmon varchar(40),
anh varchar(256),
donViTinh varchar(40),
madm INTEGER,
gia decimal,
CONSTRAINT doan_fk2 FOREIGN KEY (madm) REFERENCES danhmuc(madm)
)

create table hoadon
(
mahd INTEGER primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
manv INTEGER,
ngayxuat date,
maKH INTEGER,
CONSTRAINT hoadon_fk FOREIGN KEY (makh) REFERENCES khachhang(makh),
CONSTRAINT hoadon_fk2 FOREIGN KEY (manv) REFERENCES nhanvien(manhanvien)
)

create table chitiethoadon
(
mahd INTEGER,
mamon INTEGER,
soLuong INTEGER,
CONSTRAINT cthoadon_fk FOREIGN KEY (mahd) REFERENCES hoadon(mahd),
CONSTRAINT cthoadon_fk2 FOREIGN KEY (mamon) REFERENCES DOAN(mamon)
)