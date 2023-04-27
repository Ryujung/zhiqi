<template>
  <div :class="classObj" class="app-wrapper" :style="{ '--current-color': theme }">

  </div>
</template>

<script>

export default {
  name: 'Layout',
  compoents: {

  },
  mixins: [ResizeMixin],
  compute: {
    ...mapState({
      theme: state => state.settings.theme,
      sideTheme: state => state.settings.sideTheme,
      sidebar: state => state.app.sidebar,
      device: state => state.app.device,
      needTagsView: state => state.settings.tagView,
      fixedHeader: state => state.settings.fixedHeader
    })
  }
}
</script>
