<template><h1>Admins Management</h1></template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "AdminAdminsManagement",
  components: {},
  data() {
    return { state: this.$store.state, api: GLOBAL.apiBaseUrl };
  },
  watch: {},
  methods: { ...mapGetters([]), ...mapMutations([]), ...mapActions([]) },
};
</script>

<style lang="scss" scoped></style>
