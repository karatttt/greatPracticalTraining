<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'RuoYiDoc',
  data() {
    return {
      url: 'https://gitcode.com/zzq20226705/greatPracticalTraining/overview'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
