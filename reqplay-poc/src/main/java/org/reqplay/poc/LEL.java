package org.reqplay.poc;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.LogEventSpec;
import org.reqplay.annotation.group.LogEvents;

@LogEvents
@Doc(artifact="LEL")
public class LEL {

    @ReqItemSpec(id="LOG01", name="Evento de Log para o logon")
    @LogEventSpec
    public class Logon {}

    @ReqItemSpec(id="LOG02", name="Evento de Log para exclusão de um Aluno")
    @LogEventSpec
    public class Exclusao {}

}
