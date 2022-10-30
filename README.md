# MINI PROJECT 
Task MINI PROJECT VIX at AEON as BACKEND DEV

Muhammad Hafidz Febriansyah

# Tech Stacks Used
[![](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)]() ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) [![](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)]()

## How To Use
### REGISTER & LOGIN ACCOUNT
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/auth/login` used for login (POST)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/auth/register` used for register (POST)

### DONT FORGET IF YOU LOGIN COPY TOKEN, ADD YOUR TOKEN IN HEADER
### Karyawan
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/karyawan` use for add karyawan (POST)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/karyawan/{id}` use for getkaryawan by id (GET)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/karyawan/{id}` use for update karyawan (UPDATE)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/karyawan/list?nama=w&page=0&size=2` use for get by name and pagination (GET)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/karyawan/list` use for list all karyawan (GET)

### Training
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/training` use for add training (POST)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/training/{id}` use for get training by id (GET)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/training/{id}` use for update training (UPDATE)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/training/list?nama=w&page=0&size=2` use for get by name and pagination (GET)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/training/list` use for list all training (GET)

### Training Karyawan
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/training-karyawan` use for add training (POST)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/training-karyawan/list?size=3&page=0&nama_karyawan=h&nama_pengajar=m` use for get by name karyawan and nama pengajarj and pagination (GET)
- `https://vix-aeon-backend-hafidz.herokuapp.com/v1/training-karyawan/list` use for list all training karyawan (GET)

## REQUEST AND RESPONSE

### REGISTER & LOGIN
1.Login
    ![pp](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/auth/login.jpg)
2.Register
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/auth/register.jpg)
### KARYAWAN
1.POST
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/karyawan/post.jpg)
2.GETBYID
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/karyawan/getbyid.jpg)
3.PUT
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/karyawan/post.jpg)
4.GET-LIST-KARYAWAN-BY-NAME-AND-PAGINATION
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/karyawan/listpagination.jpg)
### TRAINING
1.POST
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/training/post.jpg)
2.GETBYID
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/trainigkaryawan/post.jpg)
3.PUT
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/training/put.jpg)
4.GET-LIST-TRAINING-BY-NAME-AND-PAGINATION
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/training/getlistpagination.jpg)
### TRAINING KARYAWAN
1.POST
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/trainigkaryawan/post.jpg)
2.GET-LIST-KARYAWAN-BY-NAME-AND-PAGINATION
    ![](https://github.com/hafidzencis/vix-aeon-task/blob/master/addimg/trainigkaryawan/getlistbypagination.jpg)

