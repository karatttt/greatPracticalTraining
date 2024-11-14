<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'RuoYiGit',
  data() {
    return {
      url: 'https://gitcode.com/zzq20226705/greatPracticalTraining'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
