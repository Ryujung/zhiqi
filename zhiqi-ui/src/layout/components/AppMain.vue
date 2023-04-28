<template>
  <seciton class="app-main">
    <transition name="fade-transform" mode="out-in">
      <keep-alive :include="cachedView">
        <router-view :key="key" />
      </keep-alive>
    </transition>
  </seciton>
</template>

<script>

export default {
  name: 'AppMain',
  data() {
    return {

    }
  },
  computed: {
    cachedView() {
      return this.$store.state.tagsView.cachedViews
    },
    key() {
      return this.$router.path
    }
  }
}
</script>
