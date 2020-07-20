<template>
  <div class="user" id="login">
    <div class="wrapC">
      <h1 class="t_center">너내동</h1>
      <div class="input-with-label">
        <input
          v-model="email"
          v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
          id="email"
          placeholder="이메일을 입력하세요."
          type="text"
        />
        <label for="email">이메일</label>
        <div class="error-text" v-if="error.email">{{error.email}}</div>
      </div>

      <div class="input-with-label">
        <input
          v-model="phonenumber"
          v-bind:class="{error : error.phonenumber, complete:!error.phonenumber&&phonenumber.length!==0}"
          id="phonenumber"
          placeholder="휴대폰 번호를 입력하세요."
          type="text"
        />
        <label for="phonenumber">휴대폰 번호</label>
        <div class="error-text" v-if="error.phonenumber">{{error.phonenumber}}</div>
      </div>

      <button
        class="btn btn--back btn--login margin-bottom"
        @click="onVerify"
        :disabled="!isSubmit"
        :class="{disabled : !isSubmit}"
      >인증번호 받기</button>
    </div>
    <div class="wrapC">
      <div class="vertification t_center">
        <div class="container__item">
          <form class="form">
            <input type="text" class="c80" placeholder="인증번호 입력" />
            <button type="button" class="c20 btn btn--primary uppercase">인증</button>
          </form>
        </div>
      </div>
      <div class="wrap t_right">
        <p>메세지가 오지 않았나요?</p>
        <router-link to="/user/findpwSuccess" class="btn--text">메세지 재발송</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import * as EmailValidator from "email-validator";
import PV from "password-validator";
import UserApi from "../../api/UserApi";

export default {
  components: {},
  created() {
    this.component = this;
    this.phoneSchema
      .is()
      .max(11)
      .has()
      .digits();
  },
  watch: {
    phonenumber: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
    }
  },
  methods: {
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.phonenumber.length >= 0 &&
        !this.phoneSchema.validate(this.phonenumber)
      )
        this.error.phonenumber = "휴대폰 번호 11자리 이어야 합니다.";
      else this.error.phonenumber = false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    onVerify() {
      if (this.isSubmit) {
        let { email, phonenumber } = this;
        let data = {
          email,
          phonenumber
        };

        //요청 후에는 버튼 비활성화
        this.isSubmit = false;

        UserApi.requestLogin(
          data,
          res => {
            //통신을 통해 전달받은 값 콘솔에 출력
            console.log(res);

            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;

            //this.$router.push("/feed/main");
          },
          error => {
            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;
          }
        );
      }
    }
  },
  data: () => {
    return {
      email: "",
      phonenumber: "",
      phoneSchema: new PV(),
      verificationcode: "",
      isSubmit: false,
      error: {
        email: false,
        phonenumber: false,
        verificationcode: false
      }
    };
  }
};
</script>