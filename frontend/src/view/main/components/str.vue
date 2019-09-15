<template>
  <div id="string">
    <Card style="width: 100%">
      <Form :label-width="80">
        <FormItem label="名称">
          <Input v-model="data.key" />
        </FormItem>
        <FormItem label="过期时间">
          <Input v-model.number="data.expiration" />
        </FormItem>
        <FormItem label="值">
          <Input v-model="data.value" type="textarea" :autosize="{minRows: 12,maxRows: 5}" />
        </FormItem>
        <FormItem>
          <Button type="primary">保存</Button>
        </FormItem>
      </Form>
    </Card>
  </div>
</template>
<script>
export default {
  props: {
    data: {
      type: Object,
      required: true,
      default: {o: 'sds'}
    }
  }
}
</script>
<style scoped>

</style>
