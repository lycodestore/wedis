<template>
  <div id="set">
    <Card style="width: 100%">
      <Form :label-width="80">
        <FormItem label="名称">
          <Input v-model="data.key" />
        </FormItem>
        <FormItem label="过期时间">
          <Input v-model.number="data.expiration" />
        </FormItem>
        <FormItem label="值">
          <Table :columns="columns" :data="list">
            <template slot="value" slot-scope="{ row, index }">
              <template v-if="row.edit">
                <Input v-model="row.value" />
              </template>
              <template v-else>
                <span>{{ row.value }}</span>
              </template>
            </template>
            <template slot="option" slot-scope="{ row, index }">
              <template v-if="row.edit">
                <Button type="success" size="small" style="margin-right: 5px" @click="saveModify(row)">保存</Button>
              </template>
              <template v-else>
                <Button type="primary" size="small" style="margin-right: 5px" @click="deleteItem(row)">修改</Button>
                <Button type="error" size="small" style="margin-right: 5px" @click="deleteItem(row)">删除</Button>
              </template>
            </template>
          </Table>
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
  data() {
    return {
      columns: [
        {
          title: '元素',
          slot: 'value',
          align: 'center'
        },
        {
          title: '操作',
          slot: 'option',
          align: 'center'
        }
      ],
      list: []
    }
  },
  props: {
    data: {
      type: Object,
      required: true,
      default: {o: 'sds'}
    }
  },
  methods: {
    deleteItem(row) {
      console.log(row)
      row.edit = true
    },
    saveModify(row) {
      row.edit = false
    }
  },
  created() {
    let list = this.data.value
    list.forEach(ele => {
      ele.edit = false
    })
    this.list = list
  }
}
</script>
<style scoped>

</style>
