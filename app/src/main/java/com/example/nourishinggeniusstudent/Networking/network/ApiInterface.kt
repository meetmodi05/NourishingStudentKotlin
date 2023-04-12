package com.example.nourishinggeniusstudent.Networking.network


import com.example.nourishinggeniusstudent.model.*
import com.example.nourishinggeniusstudent.model.Auth.SignUpModel
import com.example.nourishinggeniusstudent.model.Blog.BlogModel
import com.example.nourishinggeniusstudent.model.Career.CareerDataModel
import com.example.nourishinggeniusstudent.model.Domain.DomainModel
import com.example.nourishinggeniusstudent.model.Packages.PackageDataModel
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiInterface {
    @Multipart
    @POST("auth/register")
    fun createUser(@PartMap userData: MutableMap<String, RequestBody>): Observable<BaseModel<SignUpModel>>

    @Multipart
    @POST("career/listing")
    fun sendCareerList(@PartMap role: MutableMap<String, RequestBody>): Observable<BaseModel<CareerDataModel>>

    @Multipart
    @POST("career/details")
    fun sendCarerInfo(@PartMap role: MutableMap<String, RequestBody>): Observable<BaseModel<CareerInfoDetail>>

    @Multipart
    @POST("blog/listing")
    fun sendBlogList(@PartMap role: MutableMap<String, RequestBody>): Observable<BaseModel<List<BlogModel>>>

    @Multipart
    @POST("packages/getpackages")
    fun packages(@PartMap role: MutableMap<String, RequestBody>): Observable<BaseModel<PackageDataModel>>

    @Multipart
    @POST("domain_expert/role_listing")
    fun domainExpertRoleListing(@PartMap role: MutableMap<String, RequestBody>): Observable<BaseModel<List<DomainModel>>>

    @Multipart
    @POST("domain_expert/listing")
    fun topExpertList(@PartMap role: MutableMap<String, RequestBody>): Observable<BaseModel<List<TopExpertModel>>>

    @Multipart
    @POST("casestudy/role_listing")
    fun caseStudyRoleListing(@PartMap role: MutableMap<String, RequestBody>): Observable<BaseModel<List<CaseStudiesModel>>>

    @Multipart
    @POST("dashboard/listing")
    fun dashboardList(@PartMap role: MutableMap<String, RequestBody>): Observable<DashboardModel>
}