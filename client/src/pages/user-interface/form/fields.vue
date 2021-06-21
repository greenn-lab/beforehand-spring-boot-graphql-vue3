<template>
  <g-page>
    <div class="row">
      <div class="col-md-8 col-lg-6">
        <blockquote class="g-page--text">
          It is use to <code>input</code> or
          <code>select</code> in browser interface. By
          default, it changes the default form field
          appearance to fill and heavily modifies it to
          provide a more unique and consistent look. We
          recommend NOT using any other form field styles as
          they are not optimized.
        </blockquote>
        <blockquote class="g-page--text">
          브라우저의 <code>input</code>하고
          <code>select</code>를 사용하는 거에요. 기본적으로
          입력란의 모양을 채우도록 변경하고 보다 고유하고
          한결같은 특성을 제공하도록 많은 걸 바꿔요. 다른
          방식의 입력란은 사용 안하면 싶어요. 스타일이
          최적화되지 않았으니까요.
        </blockquote>

        <p class="g-page--subtitle">Appearance</p>
        <div class="g-page--text">
          아주 간단히 보기용 입력 양식이에요.
        </div>

        <g-form>
          <div class="g-form--content">
            <q-input v-model="appearance.name" label="이름">
              <template v-slot:prepend>
                <q-icon name="person" />
              </template>
            </q-input>
            <q-input
              v-model="appearance.email"
              label="이메일"
            >
              <template v-slot:prepend>
                <q-icon name="email" />
              </template>
            </q-input>
            <q-input
              v-model="appearance.description"
              autogrow
              counter
              maxlength="100"
              label="설명"
              type="textarea"
            >
              <template v-slot:prepend>
                <q-icon name="description" />
              </template>
            </q-input>
          </div>

          <div class="g-form--actions">
            <q-btn flat type="reset">취소하기</q-btn>
            <q-btn color="primary" type="submit">
              전송하기
            </q-btn>
          </div>
        </g-form>
      </div>
    </div>
  </g-page>
</template>

<script setup>
import { ref } from 'vue'

const appearance = ref({})
</script>
