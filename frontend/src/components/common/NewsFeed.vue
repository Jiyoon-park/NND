<template>
  <v-layout>
    <v-flex xs12 sm6 offset-sm3>
      <v-card>
        <v-card-media
          class="white--text"
          height="200px"
        >
          <v-container fill-height fluid>
            <v-layout fill-height>
              <v-flex xs12 align-end flexbox>
                <span class="headline">Title</span>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-media>
        <v-card-title>
          <div>
            <span class="grey--text">User Name</span><br>
            <span>공모전 팀원 구합니다</span><br>
            <span>같이 javascript 및 jQuery 공부할 팀원 구합니다</span>
          </div>
        </v-card-title>
        <v-card-actions>
          <v-btn flat color="orange">상세정보 보기</v-btn>
          <v-btn flat color="orange">즐겨찾기</v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  
}
</script>