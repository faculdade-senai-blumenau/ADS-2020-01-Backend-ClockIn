package br.com.senai.repository;

import br.com.senai.model.RegistroPontoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPontoModel, Integer> {

    @Query("select a\n" +
            "from registro_ponto a, usuario b\n" +
            "where a.idUsuario = b.idUsuario\n" +
            "and a.idUsuario = :idUsuario \n" +
            "order by a.dataRegistro ASC, a.horaRegistro ASC")
    List<RegistroPontoModel> findRegistroPontoUsuario(@Param("idUsuario") int idUsuario);

    @Query("select a \n" +
            "from registro_ponto a\n" +
            "where a.justificaPonto > 0 and (a.justificativaReprovacao is NULL or a.justificativaReprovacao = '')\n" +
            "order by a.idUsuario ASC, a.horaRegistro ASC")
    List<RegistroPontoModel> findRegistroPontoAprovacaoPendente();
}
