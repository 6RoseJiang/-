<template>
  <div>
    <a-button type="primary" @click="handleEdit">Add</a-button>
    <a-modal v-model:visible="visible" title="Edit Firefighter" @ok = "handleSubmit">
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
</template>

<script>
import {addFireFighter, deleteFireFighter, listFireFighter} from "@/api/api";
import {computed, reactive, ref} from "vue";

export default {
  name: "Firefighter-List",
  setup() {

    const pageNum = ref(1);
    const pageSize = ref(3);
    const total = ref(0);

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
      pagination
    }
  }
}
</script>

<style scoped>

</style>