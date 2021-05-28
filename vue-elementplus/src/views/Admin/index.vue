<template>
  <div class="adminindex">
    <router-view />
  </div>
</template>

<script type="text/javascript">
export default {
  name: "AdminIndex",
  components: {},
  data() {
    return {
      state: this.$store.state,
    };
  },
  watch: {},
  methods: {
    ...mapGetters([]),
    ...mapMutations([]),
    ...mapActions([]),
  },
};
</script>

<style lang="stylus" scoped></style>
