<template>
  <div>
    <v-layout>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title>
            <div>
              <v-icon class="mr-5">mdi-account-circle</v-icon>
              <span class="grey--text">User Name</span>
              <br />
              <span>공모전 팀원 구합니다</span>
              <br />
              <span>같이 javascript 및 jQuery 공부할 팀원 구합니다</span>
            </div>
          </v-card-title>
          <v-card-actions>
            <v-btn flat color="orange">상세정보 보기</v-btn>
            <v-btn icon color="indigo">
              <v-icon>mdi-star</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {};
</script>