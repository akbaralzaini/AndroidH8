package project.akbaralzaini.sesi2.rest;

import java.util.List;

import project.akbaralzaini.sesi2.models.Agency;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AgencyApiInterface {
    @GET("agency")
    Call<List<Agency>> getAgency();
}
