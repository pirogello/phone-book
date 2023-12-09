<template>
  <div id="app">
    <!-- <nav>
      <router-link to="/">Table</router-link> |
      <router-link to="/about">About</router-link>
    </nav> -->
    <div class="wrapper">
      <h1 class="title">Ваша телефонная книга</h1>
      <router-view/>
    </div>
  </div>
</template>

<style>
*{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
body{
  background: #FF896B;
  min-height: 100vh;
  padding: 20px 0;
}
.wrapper{
  position: relative;
  box-shadow: 0px 0px 30px rgba(0, 0, 0, .6);
  border-radius: 20px;
  max-width: 1100px;
  width: 100%;
  padding: 20px 10px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  flex-direction: column;
  background: #E8D4B4;
  min-height: calc(100vh - 40px);
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}
.button {
  color: white;
  border-radius: 5px;
  padding: 10px 15px;
  margin: 0px 0px 0px 5px;
  background: transparent;
  background: #FF896B;
  cursor: pointer;
  transition: all 0.7s ease;
  position: relative;
  display: inline-block;
  outline: none;
  text-decoration: none;
  font-size: 16px;
  border: none;
  margin-right: 10px;
}
.button:hover{
  box-shadow: inset 0px 0px 12px 0px rgba(0, 0, 0, .4);
  color: white;
}
nav {
  padding: 30px;
}
.title{
  margin-bottom: 50px;
}
</style>
