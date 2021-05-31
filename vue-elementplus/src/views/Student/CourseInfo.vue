<template></template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "StudentCourseInfo",
  components: {},
  data() {
    return { state: this.$store.state, api: GLOBAL.apiBaseUrl };
  },
  watch: {},
  methods: { ...mapGetters([]), ...mapMutations([]), ...mapActions([]) },
};
</script>

<style lang="scss" scoped></style>
