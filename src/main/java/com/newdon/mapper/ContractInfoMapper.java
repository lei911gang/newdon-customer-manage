package com.newdon.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.newdon.bo.ContractDiagramBo;
import com.newdon.entity.ContractInfo;

import java.util.List;

public interface ContractInfoMapper extends BaseMapper<ContractInfo> {
    List<ContractDiagramBo> queryDiagramWithBusinessPerson(ContractDiagramBo contractDiagramBo);

    List<ContractDiagramBo> queryDiagramWithNewsFrom(ContractDiagramBo contractDiagramBo);

    List<ContractDiagramBo> queryDiagramWithRegion(ContractDiagramBo contractDiagramBo);
}