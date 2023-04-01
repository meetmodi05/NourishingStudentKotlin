package com.example.nourishinggeniusstudent.networking.network

import com.example.nourishinggeniusstudent.model.auth.User
import com.example.nourishinggeniusstudent.model.base.BaseModel
import com.example.nourishinggeniusstudent.model.careers.CareerDetails
import com.example.nourishinggeniusstudent.model.casestudy.CaseStudyData
import com.example.nourishinggeniusstudent.model.casestudy.SortRoles
import com.example.nourishinggeniusstudent.model.data.BlogDataModel
import com.example.nourishinggeniusstudent.model.data.PackagesModel
import com.example.nourishinggeniusstudent.model.domain.DomainData
import com.example.nourishinggeniusstudent.model.response.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.*

interface APIInterface {

    @Multipart
    @POST("auth/register")
    fun register(
        @Part("name") name: RequestBody,
        @Part("email_address") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("contact_number") contactNumber: RequestBody,
        @Part("role") role: RequestBody,
    ): Observable<BaseModel<User>>

    @Multipart
    @POST("auth/login")
    fun login(
        @Part("username") email: RequestBody,
        @Part("password") password: RequestBody,
    ): Observable<BaseModel<User>>

    @Multipart
    @POST("auth/getuserinfobyid")
    fun getuserinfobyid(
        @Part("user_id") id: RequestBody,
    ): Observable<BaseModel<User>>

    @Multipart
    @POST("auth/add_user_feedback")
    fun addUserFeedback(
        @Part("user_id") id: RequestBody,
        @Part("feedback") feedback: RequestBody,
    ): Observable<BaseModel<Any?>>

    @Multipart
    @POST("auth/forgotpassword")
    fun forgotpassword(
        @Part("email_address") email: RequestBody,
    ): Observable<BaseModel<ForgotPasswordResponseModel>>

    @Multipart
    @POST("auth/confirmcode")
    fun confirmcode(
        @Part("email_address") email: RequestBody,
        @Part("confirmation_code") confirmationCode: RequestBody,
    ): Observable<BaseModel<VerifyOtpResponse>>

    @Multipart
    @POST("auth/resetpassword")
    fun resetPassword(
        @Part("user_id") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("confirm_password") confirmPassword: RequestBody,
    ): Observable<BaseModel<Any?>>

    @Multipart
    @POST("auth/deleteaccount")
    fun deleteAccount(
        @Part("user_id") id: RequestBody,
    ): Observable<BaseModel<Any?>>

    @Multipart
    @POST("auth/editprofile")
    fun editProfile(
        @PartMap map: HashMap<String, RequestBody>,
        @Part profilePic: MultipartBody.Part?,
    ): Observable<BaseModel<User>>

    @Multipart
    @POST("career/listing")
    fun getCareers(
        @Part("role") email: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<CareersListResponseModel>>

    @Multipart
    @POST("career/details")
    fun getCareerDetails(
        @Part("career_id") careerId: RequestBody,
        @Part("role") email: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<CareerDetails>>

    @Multipart
    @POST("blog/listing")
    fun getBlogs(
        @Part("role") email: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<List<BlogDataModel>>>

    @Multipart
    @POST("blog/details")
    fun getBlogData(
        @Part("id") id: RequestBody,
        @Part("role") email: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<BlogDataModel>>

    @Multipart
    @POST("casestudy/listing")
    fun getCaseStudyList(
        @PartMap requestBody: HashMap<String, RequestBody>
    ): Observable<BaseModel<CaseStudyResponseModel>>

    @Multipart
    @POST("casestudy/role_listing")
    fun getCaseStudyRoles(
        @Part("role") role: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<List<SortRoles>>>


    @Multipart
    @POST("casestudy/postbypostid")
    fun getCaseStudyData(
        @Part("casestudy_id") id: RequestBody,
        @Part("role") role: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<CaseStudyData>>

    @Multipart
    @POST("domain_expert/listing")
    fun getDomainList(
        @PartMap requestBody: HashMap<String, RequestBody>
    ): Observable<BaseModel<List<DomainData>>>

    @Multipart
    @POST("domain_expert/role_listing")
    fun getDomainRoles(
        @Part("role") role: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<List<SortRoles>>>

    @Multipart
    @POST("domain_expert/postbypostid")
    fun getDomainData(
        @Part("id") id: RequestBody,
        @Part("role") role: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<DomainData>>

    @Multipart
    @POST("dashboard/listing")
    fun getDashboardData(
        @Part("role") role: RequestBody = "student".toRequestBody(),
    ): Observable<DashboardResponseModel>

    @Multipart
    @POST("newsletter/subscription")
    fun subscribeToNewsletter(
        @Part("email_address") email_address: RequestBody,
    ): Observable<BaseModel<Any?>>


    @Multipart
    @POST("packages/getpackages")
    fun getSubscriptionPackages(
        @Part("role") role: RequestBody = "student".toRequestBody(),
    ): Observable<BaseModel<PackagesModel>>


}
