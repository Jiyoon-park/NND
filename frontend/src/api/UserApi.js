/*
 User API 예시
 */
import axios from 'axios';
const requestLogin = (data,callback,errorCallback) => {
    //백앤드와 로그인 통신하는 부분
    axios({
        method : 'GET',
        url : `http://localhost:8080/account/login`,
        params : {
            email : data.email,
            password : data.password
        }
    }).then((response) => {
        console.log(response);
        callback();
    }).catch((ex)=>{
        console.log("error", ex)
        if(ex.response.data.data=="wrong password"){
            alert("잘못된 비밀번호입니다.");
        }else if(ex.response.data.data=="wrong email"){
            alert("해당 이메일이 존재하지 않습니다.")
        }
        errorCallback();
    })

}

const UserApi = {
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
}

export default UserApi
