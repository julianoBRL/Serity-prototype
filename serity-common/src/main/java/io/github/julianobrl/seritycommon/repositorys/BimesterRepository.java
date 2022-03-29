package io.github.julianobrl.seritycommon.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import io.github.julianobrl.seritycommon.entitys.Bimester;

@Repository
public interface BimesterRepository extends JpaRepository<Bimester, Long>, JpaSpecificationExecutor<Bimester> {}
