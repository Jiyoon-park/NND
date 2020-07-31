<template>
  <v-carousel vertical-delimiters="true" vertical="true" touchless="true">
    <v-carousel-item v-for="(slide, i) in slides" :key="i">
      <v-sheet :color="colors[i]" height="100%">
        <v-row class="fill-height" align="center" justify="center">
          <div class="display-3">너 내 동</div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  data() {
    return {
      colors: ["indigo", "warning", "pink darken-2"],
      slides: ["1", "2", "3"],
    };
  },
};
</script>
