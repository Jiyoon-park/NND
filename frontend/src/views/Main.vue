<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand w-50" href="#">너내동</a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarColor02"
        aria-controls="navbarColor02"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
    </nav>
    <section>
      <div class="tabs tabs-style-linemove">
        <nav>
          <ul>
            <li>
              <a href="#section-linemove-1 " class="m-0"><span>팀원</span></a>
            </li>
            <li>
              <a href="#section-linemove-2" class="m-0"><span>팀</span></a>
            </li>
          </ul>
        </nav>
        <div class="content-wrap">
          <section id="section-linemove-1"><p>1</p></section>
          <section id="section-linemove-2"><p>2</p></section>
        </div>
        <!-- /content -->
      </div>
      <!-- /tabs -->
    </section>
  </div>
</template>
<script src="./js/cbpFWTabs.js"></script>
<script src="./js/modernizr.custom.js"></script>
<script>
(function() {
  [].slice.call(document.querySelectorAll(".tabs")).forEach(function(el) {
    new CBPFWTabs(el);
  });
})();
</script>
<script>
export default {};
</script>

<style></style>
