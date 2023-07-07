<template>

  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="collapsed" collapsible>
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys" @click="handleMenuClick" theme="dark" mode="inline">
        <a-menu-item key="Graph">
          <pie-chart-outlined />
          <span>Graphs</span>
        </a-menu-item>
        <a-menu-item key="Table">
          <desktop-outlined />
          <span>Tables</span>
        </a-menu-item>
        <a-sub-menu key="sub1">
          <template #title>
            <span>
              <user-outlined />
              <span>User</span>
            </span>
          </template>
          <a-menu-item key="3">Tom</a-menu-item>
          <a-menu-item key="4">Bill</a-menu-item>
          <a-menu-item key="5">Alex</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <template #title>
            <span>
              <team-outlined />
              <span>Team</span>
            </span>
          </template>
          <a-menu-item key="6">Team 1</a-menu-item>
          <a-menu-item key="8">Team 2</a-menu-item>
        </a-sub-menu>
        <a-menu-item key="9">
          <file-outlined />
          <span>File</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout v-if="selectedKeys.includes('Table')">
      <a-layout-header style="text-align: center; background: #002140; color: #fff; font-size: 30px; font-family: Arial, sans-serif;">Firefighter Information Display System</a-layout-header>
      <a-layout-content style="margin: 20px 16px">
        <a-breadcrumb style="margin: 16px 0px">
          <a-breadcrumb-item></a-breadcrumb-item>
        </a-breadcrumb>
        <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
          <div>
            <a-modal v-model:visible="visible" title="New Firefighter" @ok = "handleSubmit">
              <a-form :model="editableItem">
                <a-form-item label="Firefighter ID">
                  <a-input v-model:value="editableItem.firefighterId"></a-input>
                </a-form-item>
                <a-form-item label="Name">
                  <a-input v-model:value="editableItem.name"></a-input>
                </a-form-item>
                <a-form-item label="User ID">
                  <a-input v-model:value="editableItem.userId"></a-input>
                </a-form-item>
                <a-form-item label="Device Status">
                  <a-input v-model:value="editableItem.deviceStatus"></a-input>
                </a-form-item>
                <a-form-item label="Init Position">
                  <a-input v-model:value="editableItem.initPos"></a-input>
                </a-form-item>
              </a-form>
            </a-modal>
          </div>
          <a-table bordered :data-source="dataSource" :columns="columns" :pagination="pagination">
            <template #bodyCell="{column, record}">
              <template v-if="column.dataIndex === 'operation'">
                <a @click="handleDelete(record.id)">Delete</a>
              </template>
            </template>
          </a-table>
          <a-button type="primary" @click="handleEdit">Add New Firefighters</a-button>
        </div>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
      </a-layout-footer>
    </a-layout>
    <a-layout v-if="selectedKeys.includes('Graph')">
      <a-layout-header style="text-align: center; background: #002140; color: #fff; font-size: 30px; font-family: Arial, sans-serif;">Firefighter Information Display System</a-layout-header>
      <a-layout-content style="margin: 20px 16px">
        <a-breadcrumb style="margin: 16px 0px">
          <a-breadcrumb-item></a-breadcrumb-item>
        </a-breadcrumb>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>

<script>
import {addFireFighter, deleteFireFighter, listFireFighter} from "@/api/api";
import {computed, reactive, ref, defineComponent, onMounted} from "vue";
import { PieChartOutlined, DesktopOutlined, UserOutlined, TeamOutlined, FileOutlined } from '@ant-design/icons-vue';

export default defineComponent({
  name: "Firefighter-List",
  components: {
    PieChartOutlined,
    DesktopOutlined,
    UserOutlined,
    TeamOutlined,
    FileOutlined,
  },
  setup() {

    const pageNum = ref(1);
    const pageSize = ref(3);
    const total = ref(0);
    const selectedKeys = ref([]);

    onMounted(() => {
      selectedKeys.value = ['Table'];
    });

    const pagination = computed(()=>({
      current: pageNum.value,
      pageSize: pageSize.value,
      total: total.value,
      onChange: handlePageChange
    }))

    const handlePageChange=(pageNum, pageSize)=>{
      updateList(pageNum, pageSize)
    }

    const visible = ref(false);

    const editableItem = reactive({
      firefighterId: "",
      name: "",
      userId: null,
      deviceStatus: null,
      initPos: null
    })

    const columns = [{
      title: 'Firefighter ID',
      dataIndex: 'firefighterId',
      width: '30%',
    }, {
      title: 'Name',
      dataIndex: 'name',
    }, {
      title: 'User ID',
      dataIndex: 'id',
    }, {
      title: 'Device Status',
      dataIndex: 'deviceStatus',
    }, {
      title: 'Init Position',
      dataIndex: 'initPos',
    }, {
      title: 'Operation',
      dataIndex: 'operation',
    }];
    const dataSource = ref([]);

    const handleEdit = () => {
      visible.value = true;

      editableItem.firefighterId = ""
      editableItem.name = ""
      editableItem.userId = null
      editableItem.deviceStatus = null
      editableItem.initPos = null

    }

    const handleMenuClick = ({ key }) => {
      selectedKeys.value = [key];
      if (key === 'Graph') {
        // 处理 Layout 1 的点击事件
      } else if (key === 'Table') {
        // 处理 Layout 2 的点击事件
      }
    }

    const handleSubmit = () => {
      visible.value = false;

      let param = {
        firefighterId: editableItem.firefighterId,
        name: editableItem.name,
        userId: editableItem.userId,
        deviceStatus: editableItem.deviceStatus,
        initPos: editableItem.initPos,
      }

      addFireFighter(param).then(res =>{
        console.log(res.data)
      })
    }

    const handleDelete = (id) => {
      deleteFireFighter(id).then(res => {
        updateList(pageNum.value, pageSize.value)
      })
    }

    const updateList = (page, pageSize) => {
      console.log("firefighter-list")
      listFireFighter({
        pageNum: page,
        pageSize: pageSize,
      }).then(res => {
        console.log(res.data.data.records)
        dataSource.value = res.data.data.records
        total.value = res.data.data.total
        pageNum.value = res.data.data.current

      })
    }

    updateList(pageNum.value,pageSize.value);

    return{
      visible,
      updateList,
      columns,
      dataSource,
      handleDelete,
      handleEdit,
      handleSubmit,
      editableItem,
      addFireFighter,
      pagination,
      handleMenuClick,
      collapsed: ref(false),
      selectedKeys,
    }
  }
})
</script>

<style>

#components-layout-demo-side .logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout .site-layout-background {
  background: #fff;
}
[data-theme='dark'] .site-layout .site-layout-background {
  background: #141414;
}

</style>