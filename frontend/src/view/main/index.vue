<template>
  <div id="main">
    <div class="nav">
      <Menu active-name="0" @on-select="selectMenu">
        <MenuItem name="0">
          <Icon type="logo-buffer" />
          数据库0
        </MenuItem>
        <MenuItem name="1">
          <Icon type="logo-buffer" />
          数据库1
        </MenuItem>
        <MenuItem name="2">
          <Icon type="logo-buffer" />
          数据库2
        </MenuItem>
        <MenuItem name="3">
          <Icon type="logo-buffer" />
          数据库3
        </MenuItem>
        <MenuItem name="4">
          <Icon type="logo-buffer" />
          数据库4
        </MenuItem>
        <MenuItem name="5">
          <Icon type="logo-buffer" />
          数据库5
        </MenuItem>
        <MenuItem name="6">
          <Icon type="logo-buffer" />
          数据库6
        </MenuItem>
        <MenuItem name="7">
          <Icon type="logo-buffer" />
          数据库7
        </MenuItem>
        <MenuItem name="8">
          <Icon type="logo-buffer" />
          数据库8
        </MenuItem>
        <MenuItem name="9">
          <Icon type="logo-buffer" />
          数据库9
        </MenuItem>
        <MenuItem name="10">
          <Icon type="logo-buffer" />
          数据库10
        </MenuItem>
        <MenuItem name="11">
          <Icon type="logo-buffer" />
          数据库11
        </MenuItem>
        <MenuItem name="12">
          <Icon type="logo-buffer" />
          数据库12
        </MenuItem>
        <MenuItem name="13">
          <Icon type="logo-buffer" />
          数据库13
        </MenuItem>
        <MenuItem name="14">
          <Icon type="logo-buffer" />
          数据库14
        </MenuItem>
        <MenuItem name="15">
          <Icon type="logo-buffer" />
          数据库15
        </MenuItem>
      </Menu>
    </div>
    <div class="main-right">
      <Row :gutter="20">
        <Col span="8">
          <Table :columns="columns" :data="schemas">
            <template slot="type" slot-scope="{ row, index }">
              <Button type="error" size="small">{{ row.type }}</Button>
            </template>
            <template slot="option" slot-scope="{ row, index }">
              <Button type="primary" size="small" style="margin-right: 5px" @click="getData(row)">查看</Button>
            </template>
          </Table>
        </Col>
        <Col span="11">
          <nothing v-if="currentTabComponent==='Nothing'" />
          <hash v-if="currentTabComponent==='Hash'" :data="currentData" />
          <list v-if="currentTabComponent==='List'" :data="currentData" />
          <set v-if="currentTabComponent==='Set'" :data="currentData" />
          <sorted-set v-if="currentTabComponent==='SortedSet'" :data="currentData" />
          <str v-if="currentTabComponent==='Str'" :data="currentData" />
        </Col>
      </Row>
    </div>
  </div>
</template>
<script>
import Hash from './components/hash'
import List from './components/list'
import Set from './components/set'
import SortedSet from './components/sorted-sort'
import Str from './components/str'
import Nothing from './components/nothing'

export default {
  components: {
    Hash,
    List,
    Set,
    SortedSet,
    Str,
    Nothing
  },
  data() {
    return {
      columns: [
        {
          title: 'Key',
          key: 'key',
          align: 'center'
        },
        {
          title: 'Type',
          slot: 'type',
          align: 'center'
        },
        {
          title: 'Option',
          slot: 'option',
          align: 'center'
        }
      ],
      schemas: [],
      currentTabComponent: 'Nothing',
      currentData: {},
      index: 0
    }
  },
  created() {
    this.getKeys(0)
  },
  methods: {
    selectMenu(name) {
      this.currentTabComponent = 'Nothing'
      this.index = parseInt(name)
      this.getKeys(parseInt(name))
    },
    getKeys(index) {
      this.$http.post('/query/keys', {
        index: index
      }).then(res => {
        if (res.code === 20000) {
          this.schemas = res.result
        } else {
          this.$Message.error(res.message)
        }
      }).catch(err => {
        console.log(err)
      })
    },
    show(row) {
      const type = row.type
      switch (type) {
        case 'string':
          this.currentTabComponent = 'Str'
          break
        case 'hash':
          this.currentTabComponent = 'Hash'
          break
        case 'set':
          this.currentTabComponent = 'Set'
          break
        case 'zset':
          this.currentTabComponent = 'SortedSet'
          break
        case 'list':
          this.currentTabComponent = 'List'
          break
        default:
          break
      }
    },
    getData(row) {
      this.$http.post('/query/data', {
        index: this.index,
        type: row.type,
        key: row.key
      }).then(res => {
        if (res.code === 20000) {
          this.currentData = res.result
          this.show(row)
        } else {
          this.$Message.error(res.message)
        }
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style scoped>
.nav {
  position: fixed;
  left: 0;
  top: 0;
  width: 250px;
  height: 100%;
  overflow: scroll;
}
.nav::-webkit-scrollbar {
  display: none;
}
.main-right {
  padding: 10px;
  position: fixed;
  left: 250px;
  top: 0;
  height: 100%;
  width: 100%;
  overflow: scroll;
}
.main-right::-webkit-scrollbar {
  display: none;
}
</style>
